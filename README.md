# FRC Team 8092 - 2026 Robot Kodu

Bu depo, FRC (FIRST Robotics Competition) Team 8092'nin 2026 sezon robotu için yazılmış Java tabanlı robot kontrol kodunu içermektedir.

## 🤖 Proje Hakkında

Bu proje, WPILib Command-Based framework kullanılarak geliştirilmiş bir FRC robot kontrol sistemidir. Robot, mecanum sürüş sistemi, Limelight 3 kamera ile AprilTag tabanlı görüntü işleme ve otomatik hizalama özelliklerine sahiptir.

### 2026 Sezon Durumu

**Oyun:** REBUILDTM (presented by Haas)
**Kickoff:** 10 Ocak 2026

**Kickoff Öncesi Durum:**
- ✅ Mecanum şasi operasyonel ve test edilmiş
- ✅ Kod altyapısı optimize ediliyor
- ⏳ Üst mekanizma kaldırıldı (2025 robot'tan lift sistemi)
- ⏳ Yeni sezon oyun mekanikleri tasarlanıyor
- 🎯 **Yarışma:** Avrasya Regional, Türkiye (31 Mart - 2 Nisan 2026)

**Şu anki odak noktası:** Şasi ve kod iyileştirmeleri, sürücü antrenmanı, oyun stratejisi geliştirme.

### Donanım Özellikleri

- **Kontrol Sistemi:** NI roboRIO 1.0
- **Sürüş Sistemi:** 4x 2.5" CIM Motor, Mecanum tekerlekler
- **Dişli Kutuları:** AndyMark Toughbox Mini Classic (12.75:1 oran)
- **Motor Kontrolcüler:** 4x VEX Pro Victor SPX (PWM modu)
- **Gyro:** navX-MXP AHRS (MXP SPI)
- **Görüntü İşleme:** Limelight 3 kamera (10.80.92.200)
- **LED:** 300 LED'li WS2812B şerit (PWM port 9, şu anda devre dışı)
- **Kontrolcüler:** 2x Logitech gamepad

### Yazılım Özellikleri

- ✅ Mecanum sürüş sistemi (her yöne hareket)
- ✅ Gyro tabanlı field-oriented sürüş
- ✅ AprilTag otomatik hizalama (tag 12 ve 15)
- ✅ Dual PID kontrol (rotasyon + mesafe)
- ✅ İki farklı kontrolcü desteği (Xbox/Logitech)
- ✅ Slew rate limiter ile yumuşak ivmelenme
- ✅ Joystick girişlerine exponential curve uygulaması

## 📥 Projeyi Bilgisayarınıza İndirme

### 1. Repository'yi Klonlama

```bash
# Repository'yi klonlayın
git clone https://github.com/GOAT-8092/robot-2026.git

# Proje klasörüne girin
cd robot-2026
```

### 2. Fork Yapma (Katkı için İsteğe Bağlı)

1. Bu sayfanın sağ üst köşesindeki **Fork** butonuna tıklayın
2. Kendi GitHub hesabınıza fork oluşturun
3. Fork'unuzdan klonlayın

## 🛠️ Geliştirme Ortamı Kurulumu

### Gereksinimler

1. **Java 17** - [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) veya [Zulu JDK](https://www.azul.com/downloads/?package=jdk)
2. **WPILib 2025.3.2** - [WPILib Installer](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html)
3. **Git** - [Git İndir](https://git-scm.com/downloads)
4. **VS Code** (WPILib ile birlikte gelir)

### Temel Gradle Komutları

```bash
# Projeyi build etme
./gradlew build

# RoboRIO'ya deploy etme
./gradlew deploy

# Simülasyon çalıştırma
./gradlew simulateJava

# Test çalıştırma
./gradlew test

# Build artifacts temizleme
./gradlew clean
```

## 🤖 AI Araçları ile Geliştirme

Bu proje AI coding assistants ile çalışmak üzere optimize edilmiştir.

### Claude Code ile Kullanım (Önerilen)

[Claude Code](https://claude.ai/code) en önerilen yöntemdir - bu proje Claude Code ile geliştirilmiştir.

#### Kurulum:
```bash
# Claude Code CLI'yi yükleyin
npm install -g @anthropic-ai/claude-code

# Proje klasöründe Claude Code'u başlatın
cd robot-2026
claude-code
```

#### Özellikler:
- ✅ Otomatik kod analizi ve düzenleme
- ✅ `CLAUDE.md` dosyası ile proje bağlamı
- ✅ Terminal komutlarını çalıştırma
- ✅ Git entegrasyonu
- ✅ Çoklu dosya düzenleme

### VS Code ile Claude Code Kullanımı

1. VS Code'da proje klasörünü açın
2. Terminal'i açın (Ctrl+`)
3. `claude-code` komutunu çalıştırın
4. Tarayıcıda açılan Claude Code arayüzünü kullanın

### Cursor IDE ile Kullanım

1. [Cursor](https://cursor.sh/) IDE'yi indirin
2. Proje klasörünü Cursor'da açın
3. `Ctrl+K` ile AI chat'i açın
4. Kodunuz hakkında sorular sorun veya değişiklik isteyin

## 📁 Proje Yapısı

```
robot-2026/
├── src/main/java/frc/robot/
│   ├── Robot.java              # Ana robot sınıfı
│   ├── RobotContainer.java     # Subsystem ve komut yapılandırması
│   ├── Constants.java          # Tüm sabitler ve yapılandırma
│   ├── LimelightHelpers.java   # Limelight API wrapper
│   │
│   ├── commands/
│   │   ├── drive/              # Sürüş komutları
│   │   │   ├── DriveCommand.java
│   │   │   ├── DriveFieldRelativeCommand.java
│   │   │   └── StabilizeRobotCommand.java
│   │   ├── turret/             # Turret komutları
│   │   │   ├── TurretSetAngleCommand.java
│   │   │   └── TurretSetSpeedCommand.java
│   │   └── vision/             # Görüntü işleme komutları
│   │       └── AlignToAprilTagCommand.java
│   │
│   └── subsystems/
│       ├── DriveSubsystem.java          # Mecanum sürüş sistemi
│       ├── TurretSubsystem.java         # Turret kontrol sistemi
│       └── AddressableLEDSubsytem.java  # LED kontrolü
│
├── vendordeps/                  # Vendor kütüphaneleri
│   ├── Studica-2025.0.1.json   # NavX desteği
│   └── WPILibNewCommands.json
│
├── CLAUDE.md                    # Claude Code için proje dokümantasyonu
├── PARTS.md                     # Donanım listesi (İngilizce)
├── PARTS_TR.md                  # Donanım listesi (Türkçe)
├── 2026GameManual-TU.md         # 2026 Oyun Kılavuzu (Türkçe)
├── 2026GameManual-TU.pdf        # 2026 Oyun Kılavuzu PDF
└── build.gradle                 # Gradle build yapılandırması
```

## 🎮 Kontrolcü Yapılandırması

### Kontrolcü Türü Değiştirme

`Constants.java` dosyasında `ACTIVE_CONTROLLER` değişkenini düzenleyin:

```java
// Xbox veya XInput modunda Logitech için:
public static final ControllerType ACTIVE_CONTROLLER = ControllerType.XBOX;

// DirectInput modunda Logitech için:
public static final ControllerType ACTIVE_CONTROLLER = ControllerType.LOGITECH;
```

### FRC Driver Station'da Kontrolcü Değiştirme

1. FRC Driver Station'ı açın
2. **USB Devices** sekmesine tıklayın
3. Kontrolcüleri sürükleyip bırakarak yeniden düzenleyin
4. Kod değişikliği gerektirmez

### Varsayılan Kontroller

| Kontroller | İşlev |
|------------|-------|
| Sol Analog X | Sağa/Sola yanal hareket |
| Sol Analog Y | İleri/Geri |
| Z Eksen (Twist) | Dönme |
| X Butonu | AprilTag otomatik hizalama (aktifken) |
| A Butonu | Kullanılabilir |
| B Butonu | Kullanılabilir |
| Y Butonu | Kullanılabilir |
| Sol/Sağ Bumper | Kullanılabilir |

## 🔧 Önemli Notlar

### ⚠️ Kural Uyumluluğu

**Her robot değişikliği öncesi 2026 Oyun Kılavuzunu kontrol edin!**
- **Robot Kuralları (R)** - Yapı, boyut, ağırlık ve bileşen kısıtlamaları
- **Oyun Kuralları (G)** - Oynayış ve güvenlik düzenlemeleri
- **Turnuva Kuralları (T)** - Etkinlik özel gereksinimleri
- **Denetim Kuralları (I)** - Uyumluluk ve denetim kriterleri

Daha fazla bilgi için `2026GameManual-TU.md` dosyasına bakın.

### Motor İnversiyon

Motor inversiyonları `Constants.java` dosyasında kritik öneme sahiptir:
- **Sol Arka:** Ters çevrildi (inverted)
- **Sol Ön:** Ters çevrildi (inverted)
- **Sağ Ön:** Ters çevrilmedi
- **Sağ Arka:** Ters çevrilmedi

Bu ayarlar değiştirilmemelidir!

### Limelight Yapılandırması

- **IP Adresi:** `10.80.92.200`
- **Web Arayüz:** http://10.80.92.200:5801
- **Hedef TagID'ler:** 12, 15
- **Kamera:** OV5647 color rolling shutter (640x480 @ 90 FPS)
- **FOV:** 62.5° yatay, 48.9° dikey
- **Aydınlatma:** 600 lumen yeşil LED'ler

### Network Adresleri (Takım 8092)

| Cihaz | IP Adresi |
|-------|-----------|
| Radio | 10.80.92.11 |
| roboRIO | 10.80.92.4 |
| Limelight 3 | 10.80.92.200 |

## 📚 Daha Fazla Bilgi

### Proje Dokümantasyonu

- **CLAUDE.md** - Geliştiriciler için kapsamlı proje rehberi
- **PARTS.md** / **PARTS_TR.md** - Donanım spesifikasyonları ve port atamaları
- **2026GameManual-TU.md** - 2026 REBUILT oyun kılavuzu (Türkçe)

### WPILib Dokümantasyonu

- [WPILib Documentation](https://docs.wpilib.org/)
- [Command-Based Programming](https://docs.wpilib.org/en/stable/docs/software/commandbased/index.html)

### Limelight Dokümantasyonu

- [Limelight 3 Documentation](https://docs.limelightvision.io/tr/docs/docs-limelight/getting-started/limelight-3)

### Takım Kaynakları

- GitHub Organization: [GOAT-8092](https://github.com/GOAT-8092)

## 🤝 Katkıda Bulunma

1. Repository'yi fork edin
2. Yeni bir branch oluşturun (`git checkout -b feature/yeni-ozellik`)
3. Değişikliklerinizi commit edin (`git commit -m 'Yeni özellik eklendi'`)
4. Branch'inizi push edin (`git push origin feature/yeni-ozellik`)
5. Pull Request oluşturun

## 📝 Commit Mesajları

Türkçe commit mesajları kullanın:
```bash
git commit -m "Sürüş hızı limiti güncellendi"
git commit -m "AprilTag PID değerleri optimize edildi"
git commit -m "LED animasyon sistemi eklendi"
```

## 🆘 Yardım ve Destek

Sorularınız için:
- Takım mentorlarınıza danışın
- GitHub Issues kullanın
- [FRC Discord](https://discord.gg/frc) topluluğuna katılın

## 📄 Lisans

Bu proje WPILib BSD lisansı altında lisanslanmıştır.

---

**Takım:** FRC Team 8092 (G.O.A.T. - "Greatest of All Times")
**Sezon:** 2026 REBUILT
**Framework:** WPILib Command-Based Java
**Yeri:** Türkiye - Tekirdağ

🤖 *Bu proje [Claude Code](https://claude.com/claude-code) ile geliştirilmiştir*

