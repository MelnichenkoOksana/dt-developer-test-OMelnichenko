# Описание
Выполнено в рамках тестового задания на позицию junior Software Tool Engineer (Java). Тестовое задание описано в файле DT Developer Test.pdf.

# Инициализация
Проект можно запустить командой - ./gradlew run.

# Особенности реализации:
Использован JDK8 и система автоматической сборки Gradle
Для реализации проекта были использованы следующие библиотеки: 
- javax.swing.*;
- java.awt.*;
- java.io.File;
- java.io.IOException;
- java.nio.file.Files;
- java.nio.file.Path;
- java.nio.file.Paths;
- java.nio.file.StandardCopyOption;
- java.util.ArrayList;
- java.util.List.

Приложение представляет собой галерею изображений (автоматически загружаемых из папки assets) с возможностью открыть картинку в полный размер, загрузить изображение и  найти изображения по началу имени. 
Галерея изображений реализована в виде набора кнопок с отображением на каждой из кнопок уменьшенного изображения. Это решение позволяет открыть изображение по одному нажатию, а также делает галерею “отзывчивой”.
Поиск реализован по началу имени. Все полученные совпадения открываются в новом окне, также в виде набора кнопок. 
Кнопка Add File позволяет добавить новые файлы в папку assets. Окно выбора файлов по умолчанию позволяет выбирать только файлы формата .png. Копия выбранного файла помещается в папку assets. 

---
К сожалению ограничения по времени и ресурсам не позволили мне реализовать проект в полном объеме. Я планирую  улучшить проект следующим образом: 
1.	Добавить пагинацию для реализации более “приветливого” интерфейса. 
2.	Добавить обработку исключений с выводом предупреждений о нежелательных действиях пользователя (например вывод сообщения “выберите изображение” при попытке загрузить текстовый файл).
3.	Реализовать автоматическое обновление галереи после загрузки новых файлов (на данный момент загруженные файлы отображаются только после перезапуска программы).
4.	Произвести рефакторинг кода в соответствии с принципами ООП. 

При оценке прошу учесть что это мой первый опыт работы с фреймворком Swing. Буду благодарна за любую обратную связь. 
