Review - плагин для получения отзывов и предложений для вашего сервера:
Версия: v0.1

Функции:
Плагин позволит сохранить отзывы в файл в папке /plugins/Review/review.yml
Код:
- ============================================
- 'Игрок: ZeyNet'
- 'Дата: 06-01-2022 01:21:48'
- 'Отзыв: test '
- ============================================

Команды:
/review | /rv | /feedback <сообщение>
/reviewreload | /rvreload - перезагрузка config.yml


Конфиг:
\Как использовать команду. Если написать команду без сообщения/
usage: 'Используйте: /review или /rv <Отзыв/Предложение>'
\При отправке сообщения
ready: Ваше сообщение отправлено. Спасибо что помогаете улучшить сервер./
  \При успешном перезапуске конфига/
reload: Конфиг перезагружен.
  \При использовании /reviewreload | /rvreload если нету прав(review.reload)/
noperms: У вас нету прав на выполнение данной комманды.

При ипсользовании спецсимволов например двоеточие - : сообщение должно быть в ' '
​

Права:
review.reload для команды - /reviewreload | /rvreload - перезагрузка config.yml
