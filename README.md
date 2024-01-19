# Versati

  Мобильное приложение со следующим функционалом:
* **Авторизация** и **регистрация** с использованием почты;

* **QR-коды**:
  * Генерация QR-кода на основе введённых параметров;
  * Отправка QR-кода в мессенджеры или загрузка на устройство;
  * Просмотр истории генерации QR-кодов;

* **Парсинг текста**:
  * Парсинг текста с изображения по ссылке;
  * Просмотр истории парсинга текста;

* **Диаграммы и графики**:
  * Генерация диаграмм и графиков по набору данных;
  * Просмотр истории генерации диаграмм;
  * Отправка диаграмм в мессенджеры или загрузка на устройство.

## Скриншоты:
* **Авторизация и регистрация:**
<image src='https://github.com/arshapshap/versati/assets/48681339/5ef80259-70fc-405c-a96b-35dbeddc55f8' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a94402f1-882a-4377-87d7-7a3f76dfe3bb' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/7b703312-df6a-4740-9e1a-eec948a621cd' width=200 />
<br>
<br>

* **QR-коды:**

<image src='https://github.com/arshapshap/versati/assets/48681339/1c48900f-8da1-4a71-a217-c6c7b0fce752' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/8964ffac-fa4b-4d85-a3f7-b2783058461c' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/49a2eb47-7836-4f2b-b0b2-7b34ca4589f6' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a3c53a9a-ff30-4ed7-a515-34f1863d328d' width=200 />
<br>
<br>

* **Парсинг текста:**

<image src='https://github.com/arshapshap/versati/assets/48681339/b1b348b3-a79a-4be8-b305-49ac918b5a9c' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/339103bf-7496-490a-8403-427d8a8c2113' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/9d1f004b-1a71-44c2-9e65-3f1a6b80b549' width=200 />

Для примера на скриншоте использовалось [это изображение](https://favim.com/pd/s6/orig/61/text-harry-potter-hermione-Favim.com-576725.jpg).
<br>
<br>

* **Диаграммы и графики:**


<image src='https://github.com/arshapshap/versati/assets/48681339/71f35d5a-5848-43c6-abf9-4935116c34ef' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a226d052-7f3f-4787-a9c9-c7568d23285e' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/426f8f9b-fa96-4770-a0a4-691a4a7c709b' width=200 />


## Скачивание
      
  * APK-файл доступен для скачивания в разделе Releases:

    - [Version 1.2](https://github.com/arshapshap/versati/releases/tag/v1.2)

## Архитектура
* Clean Architecture
* MVI
* Многомодульность

## Используемые технологии
* Kotlin
* Android SDK
* Compose
* Orbit-MVI
* Coroutines
* Koin
* Retrofit, Kotlinx-Serialization
* Room
* Navigation Compose
* Coil
* SharedPreferences
* Firebase:
  - Authentication
  - Analytics
  - Crashlytics
  - Perfomance monitoring
  - Messaging
  - Remote Config

## Используемые API
* Генерация QR-кодов: [GoQR](https://goqr.me/api/)
* Парсинг текста с изображения: [OCR API](https://ocr.space/OCRAPI)
* Генерация диаграмм и графиков: [QuickChart](https://quickchart.io/)
