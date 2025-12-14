# Guia de Testes - RiskLocator

## Como Testar Este Aplicativo

Este guia explica como você pode testar o aplicativo RiskLocator de diferentes formas.

## 📋 Pré-requisitos

Antes de começar a testar, certifique-se de ter:

1. **Android Studio** instalado (versão Hedgehog 2023.1.1 ou superior)
2. **JDK 11** ou superior configurado
3. **Android SDK** com API Level 24 ou superior
4. Conexão com a internet (para baixar dependências)

## 🧪 Tipos de Testes Disponíveis

### 1. Testes Unitários (Unit Tests) ⚡

**O que são:** Testes rápidos que verificam a lógica do código sem precisar de um dispositivo Android.

**Onde estão:** `app/src/test/java/com/example/risklocator/ExampleUnitTest.kt`

**Como executar:**

#### Opção A: Via Terminal/Linha de Comando
```bash
# Navegue até a pasta do projeto
cd /caminho/para/RiskLocator

# No Linux/Mac:
./gradlew test

# No Windows:
gradlew.bat test

# Para ver resultados detalhados:
./gradlew test --info
```

#### Opção B: Via Android Studio
1. Abra o Android Studio
2. Abra o projeto RiskLocator
3. Aguarde a sincronização do Gradle terminar
4. No painel esquerdo (Project), navegue até:
   ```
   app → src → test → java → com.example.risklocator → ExampleUnitTest
   ```
5. Clique com o botão direito no arquivo `ExampleUnitTest.kt`
6. Selecione **"Run 'ExampleUnitTest'"**

**Resultado:**
- ✅ Verde = Teste passou
- ❌ Vermelho = Teste falhou

**Ver relatório HTML:**
Após executar os testes via terminal, abra:
```
app/build/reports/tests/testDebugUnitTest/index.html
```

### 2. Testes Instrumentados (Instrumented Tests) 📱

**O que são:** Testes que rodam em um dispositivo Android real ou emulador. Testam a interface e comportamento real do app.

**Onde estão:** `app/src/androidTest/java/com/example/risklocator/ExampleInstrumentedTest.kt`

**Preparação:**
Você precisa de um dispositivo Android conectado ou emulador rodando.

#### Como criar e iniciar um emulador:
1. No Android Studio, clique em **Tools → Device Manager**
2. Clique em **"Create Device"**
3. Escolha um dispositivo (ex: Pixel 6)
4. Selecione uma imagem do sistema (API 24 ou superior)
5. Clique em **"Finish"** e depois em **"Play"** para iniciar

#### Verificar se o dispositivo está conectado:
```bash
adb devices
```

Você deve ver algo como:
```
List of devices attached
emulator-5554   device
```

**Como executar:**

#### Opção A: Via Terminal/Linha de Comando
```bash
# Certifique-se de que o emulador está rodando
./gradlew connectedAndroidTest

# Para ver logs detalhados:
./gradlew connectedAndroidTest --info
```

#### Opção B: Via Android Studio
1. Inicie o emulador ou conecte um dispositivo físico
2. Navegue até:
   ```
   app → src → androidTest → java → com.example.risklocator → ExampleInstrumentedTest
   ```
3. Clique com o botão direito em `ExampleInstrumentedTest.kt`
4. Selecione **"Run 'ExampleInstrumentedTest'"**
5. Escolha o dispositivo/emulador na janela que aparecer

**Ver relatório HTML:**
```
app/build/reports/androidTests/connected/index.html
```

## 🚀 Comandos Úteis

### Executar Todos os Testes
```bash
# Unitários + Instrumentados
./gradlew test connectedAndroidTest
```

### Limpar e Testar
```bash
# Limpa builds anteriores e executa os testes
./gradlew clean test
```

### Build do APK
```bash
# Build debug (para desenvolvimento)
./gradlew assembleDebug

# O APK estará em:
# app/build/outputs/apk/debug/app-debug.apk
```

### Instalar no Dispositivo
```bash
# Instalar versão debug
./gradlew installDebug

# Ou manualmente:
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Ver Logs em Tempo Real
```bash
# Ver todos os logs do app
adb logcat | grep "RiskLocator"
```

## 📝 Criando Seus Próprios Testes

### Exemplo de Teste Unitário

Crie um arquivo em `app/src/test/java/com/example/risklocator/`:

```kotlin
package com.example.risklocator

import org.junit.Test
import org.junit.Assert.*

class CalculadoraTest {
    
    @Test
    fun soma_doisNumeros_retornaResultadoCorreto() {
        // Arrange (Preparar)
        val numero1 = 5
        val numero2 = 3
        
        // Act (Agir)
        val resultado = numero1 + numero2
        
        // Assert (Verificar)
        assertEquals(8, resultado)
    }
    
    @Test
    fun divisao_porZero_lancaExcecao() {
        // Arrange
        val numero = 10
        val divisor = 0
        
        // Act & Assert
        assertThrows(ArithmeticException::class.java) {
            numero / divisor
        }
    }
}
```

### Exemplo de Teste Instrumentado

Crie um arquivo em `app/src/androidTest/java/com/example/risklocator/`:

```kotlin
package com.example.risklocator

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BancoDeDadosTest {
    
    @Test
    fun testarContextoDoApp() {
        // Context da aplicação em teste
        val appContext = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            
        assertEquals("com.example.risklocator", appContext.packageName)
    }
}
```

## 🔧 Solução de Problemas

### Erro: "SDK location not found"
**Solução:**
Crie um arquivo `local.properties` na raiz do projeto com:
```properties
sdk.dir=/caminho/para/seu/Android/Sdk
```

No Windows:
```properties
sdk.dir=C\:\\Users\\SeuUsuario\\AppData\\Local\\Android\\Sdk
```

No Mac:
```properties
sdk.dir=/Users/SeuUsuario/Library/Android/sdk
```

No Linux:
```properties
sdk.dir=/home/SeuUsuario/Android/Sdk
```

### Erro: "Gradle sync failed"
**Soluções:**
```bash
# 1. Limpar cache do Gradle
./gradlew clean --refresh-dependencies

# 2. Invalidar caches do Android Studio
# No Android Studio: File → Invalidate Caches → Invalidate and Restart
```

### Erro: "No connected devices"
**Soluções:**
```bash
# 1. Verificar se o ADB vê o dispositivo
adb devices

# 2. Se nada aparecer, reinicie o ADB
adb kill-server
adb start-server

# 3. Verifique a conexão USB (para dispositivos físicos)
# - Habilite "Depuração USB" nas configurações do desenvolvedor
# - Aceite o prompt de autorização no dispositivo
```

### Testes muito lentos
**Soluções:**
1. Aumente a memória do Gradle em `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=512m
org.gradle.parallel=true
org.gradle.caching=true
```

2. Use testes unitários quando possível (não precisam de emulador)

### Emulador não inicia
**Soluções:**
1. Verifique se a virtualização está habilitada na BIOS (VT-x para Intel, AMD-V para AMD)
2. Aumente a RAM do emulador (mínimo 2GB recomendado)
3. Use uma imagem do sistema mais leve (sem Google Play Store)

## 📊 Cobertura de Código

Para gerar relatório de cobertura de código:

```bash
# Executar testes com cobertura
./gradlew testDebugUnitTest jacocoTestReport

# O relatório HTML estará em:
# app/build/reports/jacoco/testDebugUnitTest/html/index.html
```

## 🎯 Boas Práticas

1. **Sempre execute os testes antes de fazer commit**
2. **Escreva testes para cada nova funcionalidade**
3. **Mantenha os testes simples e focados**
4. **Use nomes descritivos para os testes**
5. **Organize testes em classes por funcionalidade**

## 📚 Recursos Adicionais

- [Testing no Android (Google)](https://developer.android.com/training/testing)
- [JUnit 4 Documentation](https://junit.org/junit4/)
- [Espresso Testing](https://developer.android.com/training/testing/espresso)
- [Boas Práticas de Testing](https://developer.android.com/training/testing/fundamentals)

## ❓ Precisa de Ajuda?

Se encontrar problemas:
1. Verifique as mensagens de erro completas
2. Consulte a seção "Solução de Problemas" acima
3. Verifique se todas as dependências foram baixadas corretamente
4. Tente executar `./gradlew clean build` para reconstruir do zero

---

**Última atualização:** Dezembro 2024
