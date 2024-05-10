# <image src='https://github.com/arshapshap/versati/assets/48681339/684c2581-02f6-4ba1-aa6d-bec77da5f6b3' width=50 /> Versati

  Мобильное приложение со следующим функционалом:
* **Авторизация** и **регистрация** с использованием почты;

* **QR-коды**:
  * Генерация QR-кода на основе введённых параметров;
  * Отправка QR-кода в мессенджеры или загрузка на устройство;
  * Просмотр истории генерации QR-кодов.

* **Парсинг текста**:
  * Парсинг текста с изображения по ссылке;
  * Просмотр истории парсинга текста.

* **Диаграммы и графики**:
  * Генерация диаграмм и графиков по набору данных;
  * Просмотр истории генерации диаграмм;
  * Отправка диаграмм в мессенджеры или загрузка на устройство.

* Получение **push-уведомлений**:
  * Не несёт смысловой нагрузки. Реализовано в учебных целях с использованием **Firebase Messaging**.

## Скриншоты:
* **Авторизация и регистрация:**

<image src='https://github.com/arshapshap/versati/assets/48681339/5ef80259-70fc-405c-a96b-35dbeddc55f8' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a94402f1-882a-4377-87d7-7a3f76dfe3bb' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/b6ae8de2-e74c-4856-8101-f5eb5fb3db10' width=200 />
<br>
<br>

* **QR-коды:**

<image src='https://github.com/arshapshap/versati/assets/48681339/ac5d3781-52c8-41d1-9480-881d55a75880' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a690d43a-6424-4cd6-99c4-2a6a2176fe11' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/594229c3-ef1e-44aa-9d3d-24f6ed7445f7' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a3c53a9a-ff30-4ed7-a515-34f1863d328d' width=200 />
<br>
<br>

* **Парсинг текста:**

<image src='https://github.com/arshapshap/versati/assets/48681339/a29659f3-a243-4cf7-acea-d2659d4a6b42' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/ca5e22cf-2f73-418c-be56-42fcb32bac7f' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/9d1f004b-1a71-44c2-9e65-3f1a6b80b549' width=200 />

Для примера на скриншоте использовалось [это изображение](https://favim.com/pd/s6/orig/61/text-harry-potter-hermione-Favim.com-576725.jpg).
<br>
<br>

* **Диаграммы и графики:**

<image src='https://github.com/arshapshap/versati/assets/48681339/bca5ce24-5201-442e-b92a-b908fd247bd6' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/0c8b1358-7eaa-43a1-ab4a-70f80e46a8e6' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/426f8f9b-fa96-4770-a0a4-691a4a7c709b' width=200 />


## Скачивание
      
  * APK-файл доступен для скачивания в разделе Releases:

    - [Version 1.3.1](https://github.com/arshapshap/versati/releases/tag/v1.3.1)

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
