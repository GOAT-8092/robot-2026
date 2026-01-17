#!/bin/bash
# Quick Java compilation check - runs faster than full build

# Set JAVA_HOME to use available JDK (prefer Java 22 for WPILib compatibility)
export JAVA_HOME=$(/usr/libexec/java_home -v 22 2>/dev/null || /usr/libexec/java_home -v 17 2>/dev/null || /usr/libexec/java_home -v 25 2>/dev/null)

echo "🔍 Java syntax kontrol ediliyor..."
echo ""

# Run Gradle compileJava task (faster than full build)
./gradlew compileJava --console=plain 2>&1 | tee /tmp/compile-output.txt

EXIT_CODE=${PIPESTATUS[0]}

echo ""
if [ $EXIT_CODE -eq 0 ]; then
    echo "✅ Java kodu hatasız!"
    echo ""
    echo "Özet:"
    grep -i "BUILD SUCCESSFUL\|classes compiled" /tmp/compile-output.txt | tail -1
else
    echo "❌ Java derleme hataları bulundu!"
    echo ""
    echo "Hatalar:"
    grep -A 3 "error:" /tmp/compile-output.txt | head -30
    echo ""
    echo "Daha fazla bilgi için: /tmp/compile-output.txt"
fi

exit $EXIT_CODE
