# RiskLocator

Um aplicativo Android para localização e gerenciamento de riscos desenvolvido com Kotlin.

## Requisitos

- Android Studio Hedgehog (2023.1.1) ou superior
- JDK 11 ou superior
- Android SDK com API Level 24 (Android 7.0) ou superior
- Kotlin 1.9.24

## Como Testar o Aplicativo

Este projeto contém dois tipos de testes:

### 1. Testes Unitários (Unit Tests)

Os testes unitários executam na JVM local e são mais rápidos. Não requerem um dispositivo ou emulador Android.

**Localização:** `app/src/test/java/com/example/risklocator/`

**Como executar:**

```bash
# Via linha de comando
./gradlew test

# ou especificamente para a variante debug
./gradlew testDebugUnitTest
```

**Via Android Studio:**
1. Abra o projeto no Android Studio
2. Navegue até o arquivo de teste em `app/src/test/`
3. Clique com o botão direito no arquivo ou classe de teste
4. Selecione "Run 'NomeDoTest'"

**Ver relatório de testes:**
```bash
# O relatório HTML será gerado em:
app/build/reports/tests/testDebugUnitTest/index.html
```

### 2. Testes Instrumentados (Instrumented Tests)

Os testes instrumentados executam em um dispositivo Android físico ou emulador e testam o aplicativo em um ambiente real.

**Localização:** `app/src/androidTest/java/com/example/risklocator/`

**Como executar:**

```bash
# Certifique-se de ter um emulador rodando ou dispositivo conectado
# Verifique dispositivos disponíveis:
adb devices

# Execute os testes instrumentados:
./gradlew connectedAndroidTest

# ou especificamente para a variante debug
./gradlew connectedDebugAndroidTest
```

**Via Android Studio:**
1. Inicie um emulador ou conecte um dispositivo Android
2. Navegue até o arquivo de teste em `app/src/androidTest/`
3. Clique com o botão direito no arquivo ou classe de teste
4. Selecione "Run 'NomeDoTest'"

### 3. Executar Todos os Testes

Para executar todos os testes (unitários e instrumentados):

```bash
./gradlew test connectedAndroidTest
```

## Estrutura do Projeto

```
RiskLocator/
├── app/
│   ├── src/
│   │   ├── main/              # Código principal do aplicativo
│   │   ├── test/              # Testes unitários
│   │   └── androidTest/       # Testes instrumentados
│   └── build.gradle.kts       # Configuração de build do módulo
├── build.gradle.kts           # Configuração de build raiz
├── settings.gradle.kts        # Configuração do projeto
└── gradle/
    └── libs.versions.toml     # Catálogo de versões de dependências
```

## Dependências de Teste

O projeto usa as seguintes bibliotecas para testes:

- **JUnit 4.13.2**: Framework de testes unitários
- **AndroidX Test JUnit 1.2.1**: Extensões JUnit para Android
- **Espresso 3.6.1**: Framework para testes de UI Android

## Configuração do Projeto

O projeto está configurado com:
- **compileSdk**: 35
- **minSdk**: 24 (Android 7.0)
- **targetSdk**: 35
- **Java/Kotlin**: Compatibilidade com Java 11
- **Firebase**: Analytics, Auth, Firestore e Messaging

## Build e Execução

### Build do Projeto

```bash
# Build debug
./gradlew assembleDebug

# Build release
./gradlew assembleRelease
```

### Instalar no Dispositivo

```bash
# Instalar variante debug
./gradlew installDebug

# Instalar e executar
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Verificação de Dependências

Para verificar se há atualizações de dependências disponíveis:

```bash
./gradlew dependencyUpdates
```

## Limpeza

Para limpar os arquivos de build:

```bash
./gradlew clean
```

## Solução de Problemas

### Problemas Comuns

1. **Gradle sync falhou**: 
   - Certifique-se de estar conectado à internet
   - Execute `./gradlew --refresh-dependencies`

2. **Emulador não inicia**:
   - Verifique a configuração de virtualização da CPU (VT-x/AMD-V)
   - Aumente a RAM alocada ao emulador

3. **Testes instrumentados falhando**:
   - Verifique se o dispositivo/emulador está desbloqueado
   - Execute `adb devices` para confirmar conexão

4. **Build lento**:
   - Habilite o Gradle daemon
   - Aumente a memória do Gradle em `gradle.properties`

## Integração Contínua (CI)

Para integrar os testes em um pipeline de CI:

```bash
# Execute testes unitários (não requer emulador)
./gradlew test --continue

# Para testes instrumentados em CI, considere usar:
# - Firebase Test Lab
# - AWS Device Farm
# - Emuladores em containers
```

## Recursos Adicionais

- [Documentação Oficial Android Testing](https://developer.android.com/training/testing)
- [Documentação JUnit](https://junit.org/junit4/)
- [Documentação Espresso](https://developer.android.com/training/testing/espresso)
- [Firebase Android SDK](https://firebase.google.com/docs/android/setup)

## Contribuindo

Para contribuir com testes:

1. Escreva testes unitários para lógica de negócios
2. Escreva testes instrumentados para fluxos de UI
3. Mantenha a cobertura de testes acima de 70%
4. Execute todos os testes antes de fazer commit

## Licença

[Adicione informações de licença aqui]
