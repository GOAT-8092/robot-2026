# PARTS_TR.md

FRC Takımı 8092 - 2026 Robotu için donanım bileşenleri ve parça listesi

## Mevcut Yapılandırma (Kickoff Öncesi - Ocak 2026)

**Robot Durumu**: Şasi çalışıyor, üst mekanizma çıkarıldı
- **Şasi**: Tamamen çalışır mecanum sürüş sistemi
- **Üst Mekanizma**: ÇIKARILDI (2025 kaldırma sistemi söküldü)
- **Görüntüleme Sistemi**: Limelight 3 yapılandırıldı ve çalışıyor
- **Kontrol Sistemi**: Mevcut kod tabanını çalıştıran roboRIO 1.0

**Geliştirme Odak Noktası**: 10 Ocak 2026 kickoff'ına kadar kod iyileştirmeleri ve sürücü eğitimi.

**Yarışma**: Avrasya Bölgesel, Türkiye (31 Mart - 2 Nisan 2026)

---

## Kontrol Sistemi

### Ana Kontrolcü
- **NI roboRIO 1.0** - WPILib çalıştıran ana robot kontrolcüsü
  - Ağ Adresi: 10.80.92.4

### Güç Dağıtımı
- **CTRE Power Distribution Panel (PDP)** - Robot bileşenlerine güç dağıtımı
- **CTRE Voltage Regulator Module (VRM)** - Düzenlenmiş 12V ve 5V güç sağlama
- **OptiFuse 120A Ana Şalter** - Ana devre koruması

### Ağ
- **Vivid-Hosting VH-109 FRC Radyosu** - Robot kablosuz iletişimi
  - Ağ Adresi: 10.80.92.11
  - Takım: 8092

### İnsan Arayüzü
- **2x Logitech Kontrolleri** - Sürücü ve operatör kontrolleri
  - Birincil sürücü: Port 0
  - İkincil operatör: Port 1 (kullanılırsa)
  - Sürüş kontrolü ve düğme komutları için kullanılır
  - Not: Kod, çoğu Logitech gamepad ile uyumlu olan XboxController sınıfını kullanır

## Sürüş Sistemi

### Motorlar
- **4x 2.5" CIM Motorları** - Mecanum sürüş motorları
  - **4x VEX Pro Victor SPX Motor Kontrolcüleri** tarafından kontrol edilir
  - Sol Ön: PWM Port 3 (Ters çevrildi)
  - Sol Arka: PWM Port 1 (Ters çevrildi)
  - Sağ Ön: PWM Port 2 (Ters çevrilmemiş)
  - Sağ Arka: PWM Port 4 (Ters çevrilmemiş)

### Şanzımanlar
- **4x AndyMark Toughbox Mini Classic** - Her tekerlek için bir tane
  - Dişli Oranı: 12.75:1 redüksiyon
  - Ağırlık: Her biri ~1.097g (2.42 lbs)
  - Yapı: Fiberglas dolgulu Nylon 6/6 kasa
  - Dişliler: 20 DP, 14.5° basınç açılı düz dişliler (4140 çelik)
  - Şanzıman başına 1 veya 2 CIM/NEO motoru ile uyumlu

### Sensörler
- **navX-MXP AHRS Jiroskopu**
  - Bağlantı: roboRIO üzerindeki MXP SPI portu
  - Kullanım Alanı: Alan yönlendirmeli sürüş, yön ölçümü
  - 9-eksenli IMU (jiro, ivmeölçer, manyetometre)

## Görüntüleme Sistemi

### Kamera
- **Limelight 3**
  - Ağ Adresi: 10.80.92.200 (sabit IP)
  - Kamera: OV5647 renkli rolling shutter (640x480 @ 90 FPS)
  - Görüş Alanı: 62.5° yatay, 48.9° dikey
  - Aydınlatma: 600 lumen yeşil LED'ler
  - Fonksiyon: AprilTag algılama ve izleme
  - Pipeline: Oyun parçası hizalaması için AprilTag algılama
  - Hedef Etiketler: 12, 15
  - Web Arayüzü: http://10.80.92.200:5801

## LED Sistemi

### Göstergeler
- **Adreslenebilir LED Şeridi**
  - Port: PWM 9
  - Uzunluk: 300 LED
  - Tip: WS2812B veya benzeri adreslenebilir RGB LED'ler
  - Durum: Kodda şu anda devre dışı (yorum satırı yapılmış)

## Port Atamaları Özeti

### PWM Portları
| Port | Cihaz | Notlar |
|------|--------|-------|
| 1 | Sürüüş - Sol Arka Motor | Ters çevrildi |
| 2 | Sürüüş - Sağ Ön Motor | Ters çevrilmemiş |
| 3 | Sürüüş - Sol Ön Motor | Ters çevrildi |
| 4 | Sürüüş - Sağ Arka Motor | Ters çevrilmemiş |
| 9 | Adreslenebilir LED Şeridi | Şu anda devre dışı |

### MXP/SPI Portları
| Port | Cihaz | Notlar |
|------|--------|-------|
| MXP SPI | NavX Jiroskopu | Alan yönlendirmesi |

### Ağ Cihazları
| IP Adresi | Cihaz | Notlar |
|------------|--------|-------|
| 10.80.92.11 | VH-109 FRC Radyosu | Takım 8092 kablosuz |
| 10.80.92.4 | roboRIO 1.0 | Ana kontrolcü |
| 10.80.92.200 | Limelight 3 | Görüntüleme kamerası (sabit IP) |

### USB/Kontrolcü Portları
| Port | Cihaz | Notlar |
|------|--------|-------|
| 0 | Logitech Kontroller 1 | Birincil sürücü kontrolcüsü |
| 1 | Logitech Kontroller 2 | İkincil operatör kontrolcüsü (yapılandırılmışsa) |

## Özellikler

### Sürüş Sistemi
- **Sürüş Tipi**: Mecanum (omni-yönlü)
- **Motorlar**: 4x 2.5" CIM Motorları (~5.330 RPM serbest hız)
- **Şanzımanlar**: 4x AndyMark Toughbox Mini Classic (12.75:1 oran)
- **Çıkış Hızı**: Tekerleklerde ~418 RPM (5.330 RPM ÷ 12.75)
- **Motor Kontrolcüleri**: 4x VEX Pro Victor SPX
- **Maksimum Hız**: %70 (Constants.java içinde yapılandırılabilir)
- **Slew Rate**: 2.5 birim/sn (X/Y), 2.0 birim/sn (rotasyon)

### Görüntüleme Sistemi
- **Hedef Mesafesi**: %1.3 kamera alanı
- **Maksimum Sürüş Hızı**: 0.4 (görüntüleme için azaltıldı)
- **Maksimum Rotasyon Hızı**: 0.25 (görüntüleme için azaltıldı)
- **Rotasyon PID**: kP=0.03, kI=0.0, kD=0.006
- **Sürüş PID**: kP=0.1, kI=0.0, kD=0.005

## Tedarikçi Bağımlılıkları

- **Studica**: NavX jiroskop desteği (Studica-2025.0.1.json)
- **WPILib**: Komut tabanlı framework (WPILibNewCommands.json)

## Kontrolcü Kurulumu ve Yapılandırması

### Xbox ve Logitech Kontrolleri Arasında Hızlı Geçiş

Kod artık Xbox ve Logitech kontrolleri için **iki ayrı düğme eşleşmesini** destekliyor. Aralarında geçiş yapmak için:

1. **`src/main/java/frc/robot/Constants.java` dosyasını açın**
2. **27. satırı bulun** (`ACTIVE_CONTROLLER` arayın)
3. **Kontrolcü tipini değiştirin:**
   ```java
   // Xbox veya XInput modundaki Logitech için:
   public static final ControllerType ACTIVE_CONTROLLER = ControllerType.XBOX;

   // DirectInput modundaki Logitech için:
   public static final ControllerType ACTIVE_CONTROLLER = ControllerType.LOGITECH;
   ```
4. **Kodu robota tekrar dağıtın**
5. **Bitti!** Tüm düğme eşleşmeleri otomatik olarak değişir

### Kontrolcü Port Atamasını Değiştirme

Hangi fiziksel kontrolcünün kullanılacağını değiştirmek için:

1. **FRC Driver Station'da:**
   - "USB Devices" sekmesini açın
   - Kontrolcüleri yeniden düzenlemek için sürükleyin (üst = port 0, sonraki = port 1, vb.)
   - Değişiklikler kodu yeniden dağıtmadan hemen sonra etkili olur

2. **Kod içinde (gerekirse):**
   - `src/main/java/frc/robot/Constants.java` dosyasını düzenleyin
   - `OperatorConstants` sınıfındaki `CONTROLLER_PORT` değerini değiştirin
   - Kodu robota tekrar dağıtın

### Düğme Eşleşmesi

Mevcut düğme/eksen eşleşmeleri `Constants.java` dosyasında `OperatorConstants` altında tanımlanmıştır:

**Eksenler:**
- Sol Çubuk X (eksen 0): Sol/sağ yanal hareket
- Sol Çubuk Y (eksen 1): İleri/geri
- Z-ekseni (eksen 2): Rotasyon
- Sağ Tetik (eksen 3): Kullanılabilir
- Sağ Çubuk X (eksen 4): Kullanılabilir
- Sağ Çubuk Y (eksen 5): Kullanılabilir

**Düğmeler:**
- X Düğmesi (3): AprilTag hizalaması (aktif bağlama)
- A Düğmesi (1): Kullanılabilir
- B Düğmesi (2): Kullanılabilir
- Y Düğmesi (4): Kullanılabilir
- Sol/Sağ Tamponlar (5/6): Kullanılabilir
- Başlat/Seç (7/8): Kullanılabilir
- Çubuk Düğmeleri (9/10): Kullanılabilir

### Kontrolcüleri Test Etme

Kontrolcü işlevselliğini doğrulamak için:
1. FRC Driver Station'ı açın
2. "USB Devices" sekmesine tıklayın
3. Kontrolcünüzü seçin
4. Değerlerin değiştiğini görmek için çubukları hareket ettirin ve düğmelere basın
5. Eksen numaralarının kodtaki sabitlerle eşleştiğini doğrulayın

### Logitech Kontrolcü Uyumluluğu

Kod hem XInput hem de DirectInput Logitech kontrollerini destekler:

**XInput Modu (Logitech anahtarı "X" konumunda):**
- Constants.java içinde `ControllerType.XBOX` kullanın
- Düğme eşleşmeleri Xbox kontrolcüsüyle eşleşir
- XboxController sınıfı ile sorunsuz çalışır

**DirectInput Modu (Logitech anahtarı "D" konumunda):**
- Constants.java içinde `ControllerType.LOGITECH` kullanın
- `LogitechMapping` sınıfında tanımlanan özel düğme eşleşmeleri
- Kontrolcünüz için eşleşmeleri Constants.java:57-75 içinde ayarlayın

### Logitech Düğme Eşleşmelerini Kalibrasyonu

Logitech kontrolcü düğmeleriniz varsayılanlarla eşleşmiyorsa:

1. FRC Driver Station → USB Devices sekmesini açın
2. Kontrolcünüzü seçin ve her düğmeye basın
3. Görünen düğme numaralarını not edin
4. `Constants.java` → `LogitechMapping` sınıfını düzenleyin (yaklaşık 56. satır)
5. Düğme numaralarını kontrolcünüzle eşleşecek şekilde güncelleyin
6. Kodu tekrar dağıtın

## Bileşen Detayları

### roboRIO 1.0 Özellikleri
- Birinci nesil NI roboRIO
- 667 MHz çift çekirdekli ARM Cortex-A9 işlemci
- 256 MB RAM
- NI Linux Real-Time OS çalıştırır

### 2.5" CIM Motor Özellikleri
- Tip: Fırçalı DC motor
- Serbest Hız: ~5.330 RPM
- Kilit Torku: ~2.41 N⋅m (343 oz⋅in)
- Kilit Akımı: ~131 A
- Serbest Akım: ~2.7 A
- Sürüş sistemleri için standart FRC motoru

### VEX Pro Victor SPX Motor Kontrolcü Özellikleri
- PWM kontrollü motor kontrolcüsü
- Sürekli Akım: 100A
- Tepe Akımı: 130A (2 saniye)
- 12V fırçalı DC motorlarla uyumlu
- Yerleşik termal koruma
- LED durum göstergeleri (kırmızı/turuncu/yeşil)
- Alan kısıtlı uygulamalar için kompakt tasarım
- Boyutlar: 2.75" x 1.26" x 0.65"
- Çalışma Voltajı: 6-12V

### AndyMark Toughbox Mini Classic Özellikleri
- Dişli Oranı: 12.75:1 redüksiyon
- Ağırlık: Şanzıman başına 1.097g (2.42 lbs)
- Kasa Malzemesi: Uzun fiberglas dolgulu Nylon 6/6
- Dişli Özellikleri: 20 DP, 14.5° basınç açılı düz dişliler
- Dişli Malzemesi: 4140 çelik (standart)
- Motor Montajı: 1 veya 2 CIM veya NEO motoru destekler
- Bakım: 30 dakikalık çalıştırma dönemi, periyodik yağlama için kırmızı tacky gres önerilir

### Limelight 3 Özellikleri
- OV5647 renkli rolling shutter kamera
- 90 FPS'de 640x480 çözünürlük
- 62.5° yatay FOV, 48.9° dikey FOV
- 600 lumen yeşil aydınlatma
- Donanım hızlandırmalı AprilTag algılama
- 10/100 Ethernet bağlantısı
- 5Gbps USB-A ana portu
- Yapılandırma için web arayüzü: http://10.80.92.200:5801
- Robot iletişimi için NetworkTables entegrasyonu
- Güç: 4.1V-16V giriş, maksimum 7W tüketim

### navX-MXP Yetenekleri
- 9-eksenli sensör füzyonu (jiro, ivmeölçer, pusula)
- 200 Hz güncelleme hızı
- Yaw/Pitch/Roll oryantasyonu
- Çarpışma algılama ve hareket işleme

## Notlar

- Ters motor çevirmeleri düzgün sürüş işlevi için kritiktir
- LED alt sistemi mevcut ancak şu anda devre dışı
- Ağ adresleri Takım 8092 (10.80.92.x) için yapılandırıldı
- Limelight 3 web arayüzüne http://10.80.92.200:5801 adresinden erişilebilir
- Limelight 3 dokümantasyonu: https://docs.limelightvision.io/tr/docs/docs-limelight/getting-started/limelight-3
- Driver Station'daki kontrolcü yeniden eşleştirme, kod değişikliği gerektirmeyen en kolay yöntemdir
