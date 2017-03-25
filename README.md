# yachat - Yet Another Chat
Проект для открытой лекции по автоматизированному тестированию в Android
========================

Подготавливаем окружение
------------------------
Необходимо скачать и установить:
1. Oracle JDK ( для Linux подойдет и OpenJdk )
- для Windows и Mac Os [сайт Oracle](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html "Oracle")
- для Ubuntu [сайт help ubuntu](http://help.ubuntu.ru/wiki/java "java on ubuntu")
2. Android Studio [сайт google](https://developer.android.com/studio/index.html "Android Studio")
3. git [сайт git-scm](https://git-scm.com/download/ "git")

Скачиваем исходники
------------------------
В любую папку скачиваем исходники:

 `git clone https://github.com/timmalygin/yachat.git`

после того как проект будет скопирован, необходимо запустить Android Studio и открыть наш скачанный проект.

Запуск приложения
------------------------
Перед запуском нужно создать эмулятор. [как создать эмулятор](http://aristov-vasiliy.ru/knowledge/hello-world-v-android-studio/ustanovka-emulyatora-android.html "создание эмулятор"). в Ubuntu есть проблемы с эмулятором, для правильной работы необходимо отключить compiz. 
Запуск приложения: `Run->Run 'app'`

Дополнительная информация
------------------------
1. ["Уроки по Android"](http://startandroid.ru/ru/uroki/vse-uroki-spiskom.html) немного устарели, но все еще актуальны.
2. ["Самый Важный Сайт"](https://developer.android.com/)
3. ["Espresso docs"](https://google.github.io/android-testing-support-library/docs/espresso/)
4. ["Espresso samples"](https://github.com/googlesamples/android-testing)
5. ["Hamcrest Quick Reference"](www.marcphilipp.de/blog/2013/01/02/hamcrest-quick-reference/)
6. ["Espresso Cheat Sheet"](https://google.github.io/android-testing-support-library/docs/espresso/cheatsheet/)

Задания
------------------------
1. Открыть чат с первым пользователем и написать сообщение. Проверить, что сообщение отобразилось.
2. Открыть чат с первым пользователем и проверить состояние кнопки отправки ( оно должно быть не активно ).
3. Открыть чат с каждым пользователем на планшете. Проверить, что чат открылся.
4. Открыть чат с каждым пользователем и проверить, что отображаются корректные сообщения для данного пользователя.
5. Авторизоваться под одним пользователем. Запомнить список друзей. Выйти, используя меню выхода. Зайти под другим пользователем. Проверить, что список друзей изменился.

Идентификаторы
* Фрагмент авторизации
* * **R.id.error** - TextView с описанием ошибки. При старте должна быть скрыта, отображается только при вводе неверного логина или пароля 
* * **R.id.login** - EditText для ввода логина.
* * **R.id.password** - EditText для ввода пароля.
* * **R.id.enter** - Button для входа. Если логин или пароль пустые, то недоступна ( enabled = false )
* Фрагмент со списком пользователей
* * **R.string.exit** - текст для Menu выхода. По умолчанию пункт меню скрыт. 
* * **R.id.friends_list** - RecycleView со списком друзей
* * **FriendsAdapter** - Адаптер для списка Друзей
* * **FriendHolder** - Холдер для отображения друга. Имеет метод **FriendHolder#is(Friend)** проверяющий, что Холдер отображает переданного пользователя.
* * **R.id.user_name** - TextView с именем друга
* Фрагмент с чатом пользователя
* * **R.id.messages_list** - RecycleView со списоком сообщений в чате
* * **R.id.message_input** - EditText для ввода сообщения
* * **R.id.send** - кнопка отправки сообщения. Если поле ввода пустое, то должна быть не активна
* * **MessageAdapter** - Адаптер с сообщениями. Сообщения имеют два типа: отправленые и полученные. В полученных отображается иконка отправителя
* * **R.id.message** - TextView с сообщением. 
* * **R.id.user_icon** - ImageView аватар отправителя. Есть только у полученных сообщений
* * **MessageHolder** - Холдер для отображения сообщений. Имеет метод **MessageHolder#is(Message)** проверяющий, что холдер отображает нужное сообщение

Задачки 1-4 решать в классе **SomeTest**. 5 задачу решать в классе **AuthTest**.
Для всех задач, кроме 3 и 4, нужно написать свой метод с аннотацией **@Test**. Для 3 и 4 задачи необходимо дополнить код в методе **SomeTest#enterToFriendChat**
