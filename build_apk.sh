#!/bin/bash

##############################################
# Discord Username Checker - APK Builder
# Complete automated build script
##############################################

set -e

echo ""
echo "╔════════════════════════════════════════╗"
echo "║  Discord Username Checker - APK Build  ║"
echo "║              v1.0.0                    ║"
echo "╚════════════════════════════════════════╝"
echo ""

# Color codes
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Check prerequisites
check_prerequisites() {
    echo -e "${BLUE}[1/4]${NC} Checking prerequisites..."

    if ! command -v java &> /dev/null; then
        echo -e "${RED}❌ Java not found. Install JDK 11+${NC}"
        exit 1
    fi

    if ! command -v gradle &> /dev/null; then
        if [ ! -f "./gradlew" ]; then
            echo -e "${YELLOW}⚠️  Gradle wrapper not found. Using system gradle...${NC}"
        fi
    fi

    echo -e "${GREEN}✅ Java version:${NC}"
    java -version 2>&1 | head -1
}

# Clean previous builds
clean_build() {
    echo ""
    echo -e "${BLUE}[2/4]${NC} Cleaning previous builds..."

    if [ -f "./gradlew" ]; then
        ./gradlew clean --quiet 2>/dev/null || gradle clean --quiet
    else
        gradle clean --quiet 2>/dev/null || echo "Clean skipped"
    fi

    echo -e "${GREEN}✅ Cleaned${NC}"
}

# Build APK
build_apk() {
    echo ""
    echo -e "${BLUE}[3/4]${NC} Building APK (this may take 2-5 minutes)..."
    echo ""

    if [ -f "./gradlew" ]; then
        chmod +x ./gradlew
        ./gradlew assembleRelease 2>&1 | grep -E "(BUILD|:\)|error)" || true
    else
        gradle assembleRelease 2>&1 | grep -E "(BUILD|:\)|error)" || true
    fi

    echo ""
}

# Verify and copy result
finalize() {
    echo -e "${BLUE}[4/4]${NC} Finalizing..."
    echo ""

    APK_PATH="app/build/outputs/apk/release/app-release.apk"
    OUTPUT_PATH="./DiscordChecker.apk"

    if [ -f "$APK_PATH" ]; then
        cp "$APK_PATH" "$OUTPUT_PATH"
        SIZE=$(du -h "$OUTPUT_PATH" | cut -f1)

        echo -e "${GREEN}✅ BUILD SUCCESSFUL!${NC}"
        echo ""
        echo "📱 APK Ready:"
        echo "   File: $OUTPUT_PATH"
        echo "   Size: $SIZE"
        echo ""
        echo "📲 Installation:"
        echo "   1. Transfer to your Android device"
        echo "   2. Enable 'Unknown Sources' in Settings"
        echo "   3. Tap APK to install"
        echo "   4. Grant permissions"
        echo ""
        echo "🚀 Next Steps:"
        echo "   - Launch 'Discord Checker' app"
        echo "   - Configure username length & type"
        echo "   - Start checking!"
        echo ""

    else
        echo -e "${RED}❌ BUILD FAILED${NC}"
        echo ""
        echo "Build output location: app/build/outputs/"
        echo ""
        echo "Troubleshooting:"
        echo "  • Ensure Android SDK 34+ is installed"
        echo "  • Run: gradle --version"
        echo "  • Check Internet connection"
        echo "  • Try: ./gradlew clean"
        echo ""
        exit 1
    fi
}

# Main execution
main() {
    check_prerequisites
    clean_build
    build_apk
    finalize
}

# Run main function
main

echo -e "${GREEN}Done! 🎉${NC}"
echo ""
