<template>
    <v-container style="max-width: 100%; padding: 0">
        <v-container
                :style="$vuetify.breakpoint.smAndDown ? 'max-width: 100%; padding: 0;' : 'max-width: 100%; padding: 0; background-color: #F3F9FD;'">
            <v-dialog class="dialog_student" v-model="studentDialog">
                <v-icon @click="studentDialog = false" :dialog="studentDialog" class="exit_icon" color="#00bae1">clear
                </v-icon>
                <student-dialog></student-dialog>
            </v-dialog>
            <v-container class="tiny" style="padding: 0;">
                <v-layout row class="scrollable_cards" style="padding: 20px 0 65px 0;" id="uslugi">
                    <v-flex xs10 md4 class="request_choice">
                        <v-card class="cards">
                            <v-layout row wrap>
                                <v-flex xs12 class="text-xs-center text_open_sans card_header">Студентам</v-flex>
                                <!--                                <v-flex xs12 class="text-xs-center card_description">24 ВУЗ / 156 факультетов</v-flex>-->
                                <v-flex xs12>
                                    <v-divider dark class="card_divider"></v-divider>
                                </v-flex>
                                <v-flex xs12 class="card_description_box">
                                    <div class="card_full_description text-xs-center card_full_description_NO_NETBLYA">
                                        Официальное трудоустройство <br>
                                    </div>
                                    <p class="card_full_description text-xs-center">
                                        Распределение <br>
                                        Перераспределение <br>
                                        Практика<br>
                                    </p>
                                    <div class="card_full_description text-xs-center card_full_description_NO_NETBLYA the_end">
                                        Стабильный заработок
                                    </div>
                                </v-flex>
                                <v-flex xs12>
                                    <v-btn block @click="studentDialog = !studentDialog" depressed color="#FF6600"
                                           class="card_btn"> Подробнее
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-card>
                    </v-flex>
                    <v-flex xs10 md4 class="request_choice">
                        <v-card class="cards">
                            <v-layout row wrap>
                                <v-flex xs12 class="text-xs-center text_open_sans card_header">ИП и Бизнесменам</v-flex>
                                <!--                                <v-flex xs12 class="text-xs-center card_description">1047 партнеров по всей Беларуси-->
                                <!--                                </v-flex>-->
                                <!--                                <v-flex xs6>-->
                                <!--                                    <v-layout row wrap>-->
                                <!--                                        <v-flex xs12 class="text-xs-center card_oplata_title">-->
                                <!--                                            Стоимость-->
                                <!--                                        </v-flex>-->
                                <!--                                        <v-flex xs12 class="text-xs-center card_oplata">77 руб.</v-flex>-->
                                <!--                                    </v-layout>-->
                                <!--                                </v-flex>-->
                                <!--                                <v-flex xs6>-->
                                <!--                                    <v-layout row wrap>-->
                                <!--                                        <v-flex xs12 class="text-xs-center card_oplata_title">-->
                                <!--                                            Период-->
                                <!--                                        </v-flex>-->
                                <!--                                        <v-flex xs12 class="text-xs-center card_oplata">30 дней</v-flex>-->
                                <!--                                    </v-layout>-->
                                <!--                                </v-flex>-->
                                <v-flex xs12>
                                    <v-divider dark class="card_divider"></v-divider>
                                </v-flex>
                                <!--                                <v-flex xs12 class="card_full_description_title text-xs-center text_open_sans">-->
                                <!--                                    Возможности-->
                                <!--                                </v-flex>-->
                                <v-flex xs12 class="card_description_box">
                                    <p class="card_full_description text-xs-center card_full_description_NO_NETBLYA">
                                        Освобождение от взносов в ФСЗН <br>
                                        Привлечение клиентов <br>
                                    </p>
                                    <p class="card_full_description text-xs-center">
                                        Деньги на развитие Стартапа <br>
                                        Реклама Ваших товаров или услуг <br>
                                    </p>
                                    <p class="card_full_description text-xs-center card_full_description_NO_NETBLYA the_end">
                                        Участие в наших проектах</p>
                                </v-flex>
                                <v-flex xs12>
                                    <v-btn block depressed color="#FF6600" to="/contract-request" class="card_btn">Стать
                                        Участником
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-card>
                    </v-flex>
                    <v-flex xs10 md4 class="request_choice">
                        <v-card class="cards">
                            <v-layout row wrap>
                                <v-flex xs12 class="text-xs-center text_open_sans card_header">Безработным</v-flex>
                                <!--                                <v-flex xs12 class="text-xs-center card_description">Содействие в занятости населения-->
                                <!--                                </v-flex>-->
                                <!--                                <v-flex xs6>-->
                                <!--                                    <v-layout row wrap>-->
                                <!--                                        <v-flex xs12 class="text-xs-center card_oplata_title">-->
                                <!--                                            Стоимость-->
                                <!--                                        </v-flex>-->
                                <!--                                        <v-flex xs12 class="text-xs-center card_oplata">77 руб.</v-flex>-->
                                <!--                                    </v-layout>-->
                                <!--                                </v-flex>-->
                                <!--                                <v-flex xs6>-->
                                <!--                                    <v-layout row wrap>-->
                                <!--                                        <v-flex xs12 class="text-xs-center card_oplata_title">-->
                                <!--                                            Период-->
                                <!--                                        </v-flex>-->
                                <!--                                        <v-flex xs12 class="text-xs-center card_oplata">30 дней</v-flex>-->
                                <!--                                    </v-layout>-->
                                <!--                                </v-flex>-->
                                <v-flex xs12>
                                    <v-divider dark class="card_divider"></v-divider>
                                </v-flex>
                                <!--                                <v-flex xs12 class="card_full_description_title text-xs-center text_open_sans">-->
                                <!--                                    Возможности-->
                                <!--                                </v-flex>-->
                                <v-flex xs12 class="card_description_box">
                                    <p class="card_full_description text-xs-center">
                                        Официальное трудоустройство <br>
                                        Освобождение от уплаты пошлины <br>
                                        Льгота на оплату ЖКХ <br>
                                    </p>
                                    <p class="card_full_description text-xs-center card_full_description_NO_NETBLYA the_end">
                                        Страховка <br>
                                        Трудовая пенсия
                                    </p>
                                </v-flex>
                                <v-flex xs12>
                                    <v-btn to="/contract-request" block depressed color="#FF6600" class="card_btn">
                                        Стать Участником
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-container>
        <v-container class="tiny xs_process_container">
            <v-layout row wrap class="layout_why" style="padding: 50px 0 65px 0">
                <v-flex xs12 id="how_does_it_works"><h2 class="text_open_sans h2_bestwork"
                                                        style="font-size: 40px; text-align: center; margin-bottom: 100px">
                    Как <span
                        style="color: #00bae1;">Best</span><span style="color: #203A8D;">Work</span>
                    работает?</h2>
                </v-flex>
                <v-flex md1>
                    <v-spacer></v-spacer>
                </v-flex>
                <v-flex xs12 md5 style="height: 225px" class="xs_process_box">
                    <v-layout row wrap>
                        <v-flex xs12 md4>
                            <img class="img_step" src="../img/zayavka.png"/>
                        </v-flex>
                        <v-flex xs12 md8>
                            <v-layout row wrap>
                                <v-flex xs12 class="process_box">
                                    <h4 class="process_title text_open_sans">Заявка</h4>
                                    <p align="justify" class="process_description">Позвоните нам по телефону <br>
                                        <a style="text-decoration: none" href="tel: +375256499702">+375256499702</a>
                                        или отправьте заявку через сайт. Наш сотрудник ответит на вопросы, окажет помощь
                                        и расскажет о всех преимуществах работы с нами.
                                    </p>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex md1>
                    <v-spacer></v-spacer>
                </v-flex>
                <v-flex xs12 md5 style="height: 225px" class="xs_process_box">
                    <v-layout row wrap>
                        <v-flex xs12 md4>
                            <img class="img_step" src="../img/dogovor.png"/>
                        </v-flex>
                        <v-flex xs12 md7>
                            <v-layout row wrap>
                                <v-flex xs12 class="process_box">
                                    <h4 class="process_title text_open_sans">Заключения договора</h4>
                                    <p align="justify" class="process_description">Договор на услуги, можно подписать
                                        удаленно из любого
                                        места
                                        или в нашем уютном офисе в центре Минска.
                                    </p>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex md1>
                    <v-spacer></v-spacer>
                </v-flex>
                <v-flex xs12 md5 style="height: 225px" class="xs_process_box">
                    <v-layout row wrap>
                        <v-flex xs12 md4>
                            <img class="img_step" src="../img/oplata.png"/>
                        </v-flex>
                        <v-flex xs12 md8>
                            <v-layout row wrap>
                                <v-flex xs12 class="process_box">
                                    <h4 class="process_title text_open_sans">Оплата</h4>
                                    <p class="process_description">Оплатить за наши услуги можно любым удобным способом
                                        через
                                        ЕРИП или банк.
                                    </p>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex md1>
                    <v-spacer></v-spacer>
                </v-flex>
                <v-flex xs12 md5 style="height: 225px" class="xs_process_box">
                    <v-layout row wrap>
                        <v-flex xs12 md4>
                            <img class="img_step" src="../img/usluga.png"/>
                        </v-flex>
                        <v-flex xs12 md8>
                            <v-layout row wrap>
                                <v-flex xs12 class="process_box">
                                    <h4 class="process_title text_open_sans">Получение услуги</h4>
                                    <p class="process_description">Вы получаете услугу в полном объёме в течении 7
                                        рабочих дней.
                                    </p>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
            <v-layout row class="layout_why company_description" wrap style="padding: 20px 0 120px 0">
                <v-flex xs12 md5 id="why_are_we" style="padding-top: 50px">
                    <h2 class="text_open_sans h2_why_are_we" style="font-size: 40px; margin-bottom: 15px">Почему
                        выбирают нас?</h2>
                    <p align="justify" class="process_description" style="line-height: 1.5">Наша компания уже более 5
                        лет занимается
                        поиском, наймом и управлением персоналом, а так же международным продвижением и оптимизацией
                        бизнеса. Мы обслужили более чем 20 крупнейших организации только в Беларуси, торговые,
                        транспортные и логистические центры, посольства и другие. Являемся партнерами ряда белорусских
                        банков.</p>
                    <v-layout row class="layout_galochki" wrap style="padding-top: 50px">
                        <v-flex xs12>
                            <v-layout row wrap>
                                <v-flex xs12 class="galochka_box" style="margin-bottom: 15px">
                                    <img class="galochka_ti_chas_img" src="../img/galochka_ti_chas_umresh.png"/><span
                                        class="galochka text_open_sans">Освобождение от взносов в ФСЗН</span>
                                </v-flex>
                                <v-flex xs12 class="galochka_box" style="margin-bottom: 15px">
                                    <img class="galochka_ti_chas_img" src="../img/galochka_ti_chas_umresh.png"/><span
                                        class="galochka text_open_sans">Официальное трудоустройство</span>
                                </v-flex>
                                <v-flex xs12 class="galochka_box" style="margin-bottom: 15px">
                                    <img class="galochka_ti_chas_img" src="../img/galochka_ti_chas_umresh.png"/><span
                                        class="galochka text_open_sans">Кредитование физический лиц и ИП</span>
                                </v-flex>
                                <v-flex xs12 class="galochka_box" style="margin-bottom: 15px">
                                    <img class="galochka_ti_chas_img" src="../img/galochka_ti_chas_umresh.png"/><span
                                        class="galochka text_open_sans">Распределение / Перераспределение</span>
                                </v-flex>
                                <v-flex xs12 class="galochka_box" style="margin-bottom: 15px">
                                    <img class="galochka_ti_chas_img" src="../img/galochka_ti_chas_umresh.png"/><span
                                        class="galochka text_open_sans">Практика по специальности</span>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-spacer></v-spacer>
                <v-flex xs12 md6><img style="width: 100%;" src="../img/city.png"/></v-flex>
            </v-layout>
            <v-layout row wrap style="padding: 20px 0 65px 0">
                <v-flex xs12 id="our_projects"><h2 class="text-xs-center text_open_sans our_projects"
                                                   style="font-size: 40px;margin-bottom: 50px; font-weight: bold">Наши
                    проекты</h2>
                </v-flex>
                <v-flex xs12 style="background-color: #F3F9FD; padding: 5%; margin-bottom: 15px;">
                    <v-layout row wrap>
                        <v-flex xs12 md4><img src="../img/agro.jpg"/></v-flex>
                        <v-flex xs12 md8 class="our_project_description">
                            <blockquote align="justify" class="blockquote_title">
                                Одно из самых динамично развивающихся направлений туристической деятельности и
                                прекрасная возможность для жителей города получить полноценный отдых на природе. Начата
                                подготовка к строительству Агроусадьбы. Данный объект находится всего 35 км. от г.
                                Минска, площадью 18,2 Га, имеет 5 назначений земли.
                            </blockquote>
                            <i class="material-icons" style="vertical-align: middle;">account_circle</i> <span
                                class="blockquote_author">Агротуризм</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs12 style="background-color: #F3F9FD; padding: 5%; margin-bottom: 15px;">
                    <v-layout row wrap>
                        <v-flex xs12 md4><img src="../img/tvoedelo.jpg"/></v-flex>
                        <v-flex xs12 md8 class="our_project_description">
                            <blockquote align="justify" class="blockquote_title">
                                Оказываем услуги работодателям по поиску и подбору персонала и представляем услуги
                                соискателям по поиску работы и трудоустройству, а также другие сопутствующие услуги.
                            </blockquote>
                            <i class="material-icons" style="vertical-align: middle;">account_circle</i> <span
                                class="blockquote_author">Рекрутинговое агентство</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs12 style="background-color: #F3F9FD; padding: 5%; margin-bottom: 15px;">
                    <v-layout row wrap>
                        <v-flex xs12 md4><img src="../img/torgcompany.jpg"/></v-flex>
                        <v-flex xs12 md8 class="our_project_description">
                            <blockquote align="justify" class="blockquote_title">

                                Компания предоставляет надежный сервис, обеспечивая комплексное обслуживание заказчиков
                                различными товарами: продуктами питания, напитками, бытовой химии, товарами для домашних
                                животных и многое другое во все регионы Баларуси и стран СНГ.
                            </blockquote>
                            <i class="material-icons" style="vertical-align: middle;">account_circle</i> <span
                                class="blockquote_author">Торговая компания</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs12 style="background-color: #F3F9FD; padding: 5%; margin-bottom: 15px;">
                    <v-layout row wrap>
                        <v-flex xs12 md4><img src="../img/itcompany.jpg"/></v-flex>
                        <v-flex xs12 md8 class="our_project_description">
                            <blockquote align="justify" class="blockquote_title">
                                Комплексные услуги в сфере ИТ решений для частных лиц и бизнеса. Разработка програмного
                                обеспечения, сайтов, продвижение товаров и услуг в сети Интернет.
                            </blockquote>
                            <i class="material-icons" style="vertical-align: middle;">account_circle</i> <span
                                class="blockquote_author">IT компания</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>

            <v-layout class="text-xs-center text_open_sans FAQ_header"
                      style="font-size: 40px; font-weight: bold; margin-bottom: 50px; display: block" id="FAQ">Ответы на
                часто
                задаваемые вопросы
            </v-layout>
            <v-layout row style="padding: 20px 0 65px 0">
                <v-divider></v-divider>
                <v-flex xs12>
                    <v-layout row wrap>
                        <v-flex xs12 style="cursor: pointer" @click="guestion = guestion === 1 ? null : 1">
                            <v-layout class="question_title">
                                <v-flex xs1 class="text-xs-center" style="font-weight: bold">1</v-flex>
                                <v-flex xs10>
                                    <v-layout row wrap>
                                        <v-flex xs12 class="question_header">Законно ли это?</v-flex>
                                        <v-flex xs12>
                                            <v-container v-show="guestion === 1" class="expand_answer">
                                                Мы уже более 5 лет занимаемся помощью в распределении, перераспределении
                                                и прохождении практики студентов,
                                                оказываем услуги оптимизации бизнеса для бизнеса и содействуем в
                                                занятости безработного населения.
                                                Все это не могло быть возможным, если бы не большое внимание, которое мы
                                                уделяем законности всего, что мы делаем. <br>
                                                За более подробной информацией вы можете позвонить нам по номеру <a
                                                    href="tel:+375256499702">+375 (25) 649-97-02</a> или написать нам в
                                                Viber на этот номер.
                                            </v-container>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex xs1 v-if="guestion !== 1"><i
                                        class="material-icons get_answer get_answer">add</i></v-flex>
                                <v-flex xs1 v-else><i class="material-icons get_answer">remove</i></v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex xs12 style="cursor: pointer" @click="guestion = guestion === 2 ? null : 2">
                            <v-layout class="question_title">
                                <v-flex xs1 class="text-xs-center" style="font-weight: bold">2</v-flex>
                                <v-flex xs10>
                                    <v-layout row wrap>
                                        <v-flex xs12 class="question_header">Смогу ли я остаться на своей работе /
                                            заниматься и дальше своим делом?
                                        </v-flex>
                                        <v-flex xs12>
                                            <v-container v-show="guestion === 2" class="expand_answer">
                                                Вы сможете остаться на нынешнем месте работы или заниматься своим делом.
                                                <br>
                                                Трудоустройство у нас никак не повлияет на Вашу нынешнюю работу или Ваше
                                                дело.
                                            </v-container>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex xs1 v-if="guestion !== 2"><i class="material-icons get_answer">add</i></v-flex>
                                <v-flex xs1 v-else><i class="material-icons get_answer">remove</i></v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex xs12 style="cursor: pointer" @click="guestion = guestion === 3 ? null : 3">
                            <v-layout class="question_title">
                                <v-flex xs1 class="text-xs-center" style="font-weight: bold">3</v-flex>
                                <v-flex xs10>
                                    <v-layout row wrap>
                                        <v-flex xs12 class="question_header">Что нужно принести для трудоустройства?
                                        </v-flex>
                                        <v-flex xs12>
                                            <v-container v-show="guestion === 3" class="expand_answer">
                                                Для трудоустройства вам будут необходимы следующие документы: <br>
                                                - Паспорт <br>
                                                - Документ об образовании <br>
                                                - Карта социального страхования <br>
                                                - Трудовая книга <br>
                                                - Военный билет или приписное свидетельство (для мужчин) <br>
                                                Если Вы молодой специалист на распределение/перераспределение, тогда вам
                                                также необходимо будет при себе иметь отрывной розовый бланк от
                                                учреждения образования.
                                            </v-container>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex xs1 v-if="guestion !== 3"><i class="material-icons get_answer">add</i></v-flex>
                                <v-flex xs1 v-else><i class="material-icons get_answer">remove</i></v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex xs12 style="cursor: pointer" @click="guestion = guestion === 4 ? null : 4">
                            <v-layout class="question_title">
                                <v-flex xs1 class="text-xs-center" style="font-weight: bold">4</v-flex>
                                <v-flex xs10>
                                    <v-layout row wrap>
                                        <v-flex xs12 class="question_header">Как происходит
                                            распределение/перераспределение к вам?
                                        </v-flex>
                                        <v-flex xs12>
                                            <v-container v-show="guestion === 4" class="expand_answer">
                                                При поступлении Вашей заявки на распределение/перераспределение мы
                                                готовим письмо в Ваше учреждение образования и письмо на ваше нынешнее
                                                место отработки (если вы собираетесь перераспределяться к нам). В
                                                последующем Вы едете на нынешнее место отработки, получаете письмо, что
                                                они не против перераспределения и потом едете в Ваше учреждения
                                                образования, где получаете розовый бланк, с которым оформляетесь у нас
                                                по контракту. Необходимые документы можно посмотреть в вопросе 4.
                                            </v-container>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex xs1 v-if="guestion !== 4"><i class="material-icons get_answer">add</i></v-flex>
                                <v-flex xs1 v-else><i class="material-icons get_answer">remove</i></v-flex>
                            </v-layout>
                        </v-flex>
                        <!--                        <v-flex xs12 style="cursor: pointer" @click="guestion = guestion === 5 ? null : 5">-->
                        <!--                            <v-layout class="question_title">-->
                        <!--                                <v-flex xs1 class="text-xs-center" style="font-weight: bold">5</v-flex>-->
                        <!--                                <v-flex xs10>-->
                        <!--                                    <v-layout row wrap>-->
                        <!--                                        <v-flex xs12>Что нужно принести для трудоустройства?</v-flex>-->
                        <!--                                        <v-flex xs12>-->
                        <!--                                            <v-container v-show="guestion === 5" class="expand_answer">потому что-->
                        <!--                                                ааааааааааааааааааааа-->
                        <!--                                            </v-container>-->
                        <!--                                        </v-flex>-->
                        <!--                                    </v-layout>-->
                        <!--                                </v-flex>-->
                        <!--                                <v-flex xs1 v-if="guestion !== 5"><i class="material-icons get_answer">add</i></v-flex>-->
                        <!--                                <v-flex xs1 v-else><i class="material-icons get_answer">remove</i></v-flex>-->
                        <!--                            </v-layout>-->
                        <!--                        </v-flex>-->
                        <!--                        <v-flex xs12 style="cursor: pointer" @click="guestion = guestion === 6 ? null : 6">-->
                        <!--                            <v-layout class="question_title">-->
                        <!--                                <v-flex xs1 class="text-xs-center" style="font-weight: bold">6</v-flex>-->
                        <!--                                <v-flex xs10>-->
                        <!--                                    <v-layout row wrap>-->
                        <!--                                        <v-flex xs12>Что нужно принести для трудоустройства?</v-flex>-->
                        <!--                                        <v-flex xs12>-->
                        <!--                                            <v-container v-show="guestion === 6" class="expand_answer">потому что-->
                        <!--                                                ааааааааааааааааааааа-->
                        <!--                                            </v-container>-->
                        <!--                                        </v-flex>-->
                        <!--                                    </v-layout>-->
                        <!--                                </v-flex>-->
                        <!--                                <v-flex xs1 v-if="guestion !== 6"><i class="material-icons get_answer">add</i></v-flex>-->
                        <!--                                <v-flex xs1 v-else><i class="material-icons get_answer">remove</i></v-flex>-->
                        <!--                            </v-layout>-->
                        <!--                        </v-flex>-->
                        <!--                        <v-flex xs12 style="cursor: pointer" @click="guestion = guestion === 7 ? null : 7">-->
                        <!--                            <v-layout class="question_title">-->
                        <!--                                <v-flex xs1 class="text-xs-center" style="font-weight: bold">7</v-flex>-->
                        <!--                                <v-flex xs10>-->
                        <!--                                    <v-layout row wrap>-->
                        <!--                                        <v-flex xs12>Что нужно принести для трудоустройства?</v-flex>-->
                        <!--                                        <v-flex xs12>-->
                        <!--                                            <v-container v-show="guestion === 7" class="expand_answer">потому что-->
                        <!--                                                ааааааааааааааааааааа-->
                        <!--                                            </v-container>-->
                        <!--                                        </v-flex>-->
                        <!--                                    </v-layout>-->
                        <!--                                </v-flex>-->
                        <!--                                <v-flex xs1 v-if="guestion !== 7"><i class="material-icons get_answer">add</i></v-flex>-->
                        <!--                                <v-flex xs1 v-else><i class="material-icons get_answer">remove</i></v-flex>-->
                        <!--                            </v-layout>-->
                        <!--                        </v-flex>-->
                        <!--                        <v-flex xs12 style="cursor: pointer" @click="guestion = guestion === 8 ? null : 8">-->
                        <!--                            <v-layout class="question_title">-->
                        <!--                                <v-flex xs1 class="text-xs-center" style="font-weight: bold">8</v-flex>-->
                        <!--                                <v-flex xs10>-->
                        <!--                                    <v-layout row wrap>-->
                        <!--                                        <v-flex xs12>Что нужно принести для трудоустройства?</v-flex>-->
                        <!--                                        <v-flex xs12>-->
                        <!--                                            <v-container v-show="guestion === 8" class="expand_answer">потому что-->
                        <!--                                                ааааааааааааааааааааа-->
                        <!--                                            </v-container>-->
                        <!--                                        </v-flex>-->
                        <!--                                    </v-layout>-->
                        <!--                                </v-flex>-->
                        <!--                                <v-flex xs1 v-if="guestion !== 8"><i class="material-icons get_answer">add</i></v-flex>-->
                        <!--                                <v-flex xs1 v-else><i class="material-icons get_answer">remove</i></v-flex>-->
                        <!--                            </v-layout>-->
                        <!--                        </v-flex>-->

                    </v-layout>
                </v-flex>
            </v-layout>

            <!--            <v-layout class="text-xs-center text_open_sans FAQ_header"-->
            <!--                      style="font-size: 40px; font-weight: bold; margin-bottom: 50px; display: block" id="about"> О нас-->
            <!--            </v-layout>-->
            <!--            <v-layout row style="padding: 20px 0 65px 0">-->
            <!--                <v-divider></v-divider>-->
            <!--                <v-flex xs12><img class="about_logo" src="../img/logo-alexhur.jpg"> Креативная студия Алексея Гуревича-->
            <!--                    занимается-->
            <!--                </v-flex>-->
            <!--            </v-layout>-->
            <v-layout id="reviews" class="text-xs-center text_open_sans FAQ_header"
                      style="font-size: 40px; font-weight: bold; margin-bottom: 50px; display: block"> О нас в соц.
                сетях
            </v-layout>
            <v-layout row wrap class="review_box_layout">
                <v-flex xs12 md4>
                    <v-layout row wrap class="review_box">
                        <v-flex xs12 class="text-xs-center">
                            <img src="https://sun9-46.userapi.com/impf/c855320/v855320988/6ceaf/pXtbIkzBj3Q.jpg?size=200x0&amp;quality=90&amp;sign=e22e42b1ff127e9a24e1dbd12541f9a0"
                                 class="review_img">
                        </v-flex>
                        <v-flex xs12 class="text-xs-center review_header">
                            Марченко Владислав
                        </v-flex>
                        <v-spacer></v-spacer>
                        <v-flex xs12 class="text-xs-center review_body">
                            Обратился в компанию в августе 2019 г. Очень помогли выбраться из аг. Горки с прежнего места
                            отработки) Андрей сопровождал все время от письма до подписания контракта. В общем, все
                            хорошо, спасибо вам)
                        </v-flex>
                        <v-flex xs12 class="text-xs-center">
                            <v-btn @click="goToReview" style="text-transform: none" class="review_btn" flat>
                                Перейти к отзыву
                                <v-icon dark class="review_btn_icon">play_arrow</v-icon>
                            </v-btn>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-container>


        <v-container style="max-width: 100%; padding: 0; background-color: #F3F9FD;">
            <v-container class="tiny">
                <v-layout row wrap style="padding: 20px 0 65px 0">
                    <v-flex xs12><h4 class="text_open_sans"
                                     style="color: #161922; font-size: 20px; font-weight: 100; float: left">Наши
                        партнеры</h4>
                        <div style="float: right;">
                            <i class="material-icons navigate_arrows" @click="back()">keyboard_arrow_left</i>
                            <i class="material-icons navigate_arrows" @click="next()">keyboard_arrow_right</i>
                        </div>
                    </v-flex>
                    <v-flex xs12 style="position: relative; height: 120px; overflow: hidden">
                        <v-layout id="carousel" row style="position: absolute; left: 0">
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_1.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_2.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_3.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_4.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_5.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_6.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_7.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_8.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_9.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_10.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_11.png"/>
                            </v-flex>
                            <v-flex xs3 class="obertka_carousel">
                                <img class="img_carousel" src="../img/slider_12.png"/>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-container>
    </v-container>

</template>

<script>
    import StudentDialog from '../components/Dialogs/StudentDialog.vue'

    export default {
        name: "Index",
        components: {
            StudentDialog
        },
        data() {
            return {
                guestion: null,
                left: 0,
                maxLeft: 0,
                studentDialog: false,
            }
        },
        methods: {
            goToReview() {
                window.open('https://vk.com/public193130543?w=wall-193130543_3');
            },
            back() {
                if (!this.$vuetify.breakpoint.smAndDown) {
                    if (this.left !== 0) {
                        let back = this.left + 1100;
                        this.left = back;
                        document.getElementById("carousel").style.left = back + "px"
                    } else {
                        this.left = this.maxLeft;
                        document.getElementById("carousel").style.left = this.maxLeft + "px"
                    }
                } else {
                    if (this.left < 0) {
                        let back = this.left + document.getElementById("carousel").clientWidth / document.getElementsByClassName("obertka_carousel").length;
                        this.left = back;
                        document.getElementById("carousel").style.left = back + "px"
                    } else {
                        this.left = this.maxLeft;
                        document.getElementById("carousel").style.left = this.maxLeft + "px"
                    }
                }
            },
            next() {
                if (!this.$vuetify.breakpoint.smAndDown) {
                    if (this.left !== this.maxLeft) {
                        let next = this.left - 1100;
                        this.left = next;
                        document.getElementById("carousel").style.left = next + "px"
                    } else {
                        this.left = 0;
                        document.getElementById("carousel").style.left = 0 + "px"
                    }
                } else {
                    if (this.left >= this.maxLeft && this.left !== this.maxLeft) {
                        let next = this.left - document.getElementById("carousel").clientWidth / document.getElementsByClassName("obertka_carousel").length;
                        this.left = next;
                        document.getElementById("carousel").style.left = next + "px"
                    } else {
                        this.left = 0;
                        document.getElementById("carousel").style.left = 0 + "px"
                    }
                }
            }
        },
        mounted() {
            if (!this.$vuetify.breakpoint.smAndDown) {
                this.maxLeft = -((document.getElementsByClassName("obertka_carousel").length / 3 - 1) * 1100)
                // document.getElementsByClassName("scrollable_cards")[0].scrollLeft = document.getElementsByClassName("scrollable_cards")[0].clientWidth / 1.5
            } else {
                this.maxLeft = -document.getElementById("carousel").clientWidth / document.getElementsByClassName("obertka_carousel").length * (document.getElementsByClassName("obertka_carousel").length - 1)
                // document.getElementsByClassName("scrollable_cards")[0].scrollLeft = document.getElementsByClassName("scrollable_cards")[0].clientWidth / 1.5
            }
        },
        watch: {
            guestion: function (oldValue, newValue) {
                if (newValue !== null) {
                    document.getElementsByClassName("question_title")[newValue - 1].classList.remove("triggered_question");
                }
                if (oldValue !== null) {
                    document.getElementsByClassName("question_title")[oldValue - 1].classList.add("triggered_question");
                }
            },
            deep: true
        }
    }
</script>

<style scoped>
    @media only screen and (min-width: 1110px) {
        .container .tiny {
            max-width: 1102px;
            padding: 0;
        }
    }

    @media screen and (max-width: 960px) {
        .review_icon {
            font-size: 17vw;
        }

        .review_header {
            font-size: 6vw;
            margin: 10px 0;
        }

        .review_body {
            text-align: justify !important;
            font-family: 'Open Sans', sans-serif;
            font-size: 4vw;
        }

        .review_btn {
            margin-top: 25px;
        }

        .review_btn_icon {
            font-size: 4vw;
        }

        .review_box {
            background: white;
            border-radius: 41px;
            padding: 7vw;
            margin: 0 10px;
        }

        .review_box_layout {
            margin-bottom: 50px;
        }

        .about_logo {

        }

        .question_title {
            align-items: center;
        }

        .expand_answer {
            padding-top: 20px;
        }

        .question_header {
            line-height: normal;
        }

        .dialog_student_box {
            background-position-x: -627px;
        }

        .exit_icon {
            font-size: 6vw !important;
            right: 9vw !important;
            padding-top: 1vh !important;
        }

        .dialog_student {

        }

        .dialog_student_box {

        }

        .card_description_box {
            height: 40vh;
        }

        .the_end {
            margin-bottom: 6vh !important;
        }

        .card_full_description_NO_NETBLYA {
            color: #00bae1 !important;
            font-size: 4vw !important;
        }

        .card_full_description {
            font-size: 6vw;
            color: white;
            margin-bottom: 0;
            font-weight: 700;
            /*6vh*/
        }

        .card_full_description_title {
            font-size: 6vw;
            margin-bottom: 2vh;
            color: white;
        }

        .get_answer {
            font-size: 3vw;
        }

        .FAQ_header {
            font-size: 6vw !important;
            margin-bottom: 2vw !important;
        }

        .our_project_description {
            text-align: start;
        }

        .our_projects {
            font-size: 8vw !important;
            margin-bottom: 2vw !important;
        }

        .layout_galochki {
            padding: 0;
        }

        .galochka_ti_chas_img {
            width: 8vw;
        }

        .galochka_box {
            font-size: 1vw;
        }

        .company_description {
            text-align: start;
        }

        .layout_why {
            padding: 0 !important;
            padding-top: 10vh !important;
        }

        .h2_why_are_we {
            font-size: 10vw !important;
            text-align: center;
        }

        .xs_process_box {
            height: auto !important;
            margin-bottom: 6vh;
        }

        .xs_process_container {
            text-align: center;
            padding: 0 25px;
        }

        .img_step {
            width: 30vw;
        }

        .h2_bestwork {
            font-size: 9vw !important;
            margin-bottom: 9vh !important;
        }

        .cards {
            width: 72vw;
            height: 66vh;
        }

        .scrollable_cards {
            overflow-x: auto;
            padding: 2vh 0px 7vh !important;
            -webkit-overflow-scrolling: touch;
        }

        .scrollable_cards::-webkit-scrollbar {
            display: none;
        }

        .request_choice {
            padding: 0 2vw;
        }

        .card_header {
            color: white;
            font-size: 5vw;
            font-weight: 800;
            margin-bottom: 2vh;
        }

        .card_description {
            color: #00bae1;
            margin-bottom: 3vh;
        }

        .card_oplata {
            font-weight: bold;
            margin-bottom: 3vh;
            color: white;
            font-size: 5vw;
        }

        .card_oplata_title {
            margin-bottom: 2vh;
            color: white;
        }

        .card_divider {
            margin-bottom: 2vh;
        }

        .card_btn {
            height: 7vh;
            font-size: 4vw !important;
        }

        .galochka {
            font-size: 4vw;
            margin-left: 1vw;
        }

        .obertka_carousel {
            width: 89.2vw;
        }
    }

    @media screen and (min-width: 960px) {
        .review_icon {
            font-size: 4vw;
        }

        .review_header {
            font-size: 1vw;
            margin: 10px 0;
        }

        .review_body {
            text-align: justify !important;
            font-family: 'Open Sans', sans-serif;
        }

        .review_btn {
            margin-top: 25px;
        }

        .review_btn_icon {
            font-size: 1vw;
        }

        .review_box {
            background: white;
            border-radius: 41px;
            padding: 2vw;
            margin: 0 10px;
        }

        .review_box_layout {
            margin-bottom: 50px;
        }

        .dialog_student {

        }

        .dialog_student_box {

        }

        .card_description_box {
            height: 150px;
        }

        .card_full_description {
            color: white;
            margin-bottom: 15px;
        }

        .obertka_carousel {
            width: 340px;
        }

        .galochka {
            font-size: 20px;
            margin-left: 15px;
        }

        .process_box {
            padding-top: 30px;
        }

        .card_btn {
            height: 50px;
        }

        .cards {
            width: 95%;
            height: 355px;
        }

        .card_header {
            color: white;
            font-size: 25px;
            font-weight: 800;
            margin-bottom: 10px;
        }

        .card_description {
            color: #00bae1;
            margin-bottom: 35px;
        }

        .card_oplata {
            font-weight: bold;
            margin-bottom: 25px;
            color: white;
            font-size: 16px;
        }

        .card_oplata_title {
            margin-bottom: 15px;
            color: white;
        }

        .card_divider {
            margin-bottom: 35px;
        }

        .card_full_description_title {
            font-size: 24px;
            margin-bottom: 15px;
            color: white;
        }
    }

    .cards {
        padding: 10%;
        background-image: linear-gradient(#000080, #0080FF);
    }

    .navigate_arrows {
        cursor: pointer;
    }

    .obertka_carousel {
        margin: 13px;
        height: 95px;
        background-color: rgba(0, 0, 0, 0.1);
        padding: 10px;
    }

    .img_carousel {
        max-width: 100%;
        height: auto;
        display: block;
        margin: auto;
    }

    #carousel {
        -webkit-transition: left 1s;
        transition: left 1s;
    }

    .text_open_sans {
        font-family: 'Open Sans', sans-serif;
    }

    .card_divider {
        border: 1px solid rgba(255, 255, 255, .2);
        border-top-color: rgba(255, 255, 255, 0.2);
        border-top-style: solid;
        border-top-width: 1px;
        border-right-color: rgba(255, 255, 255, 0.2);
        border-right-style: solid;
        border-right-width: 1px;
        border-bottom-color: rgba(255, 255, 255, 0.2);
        border-bottom-style: solid;
        border-bottom-width: 1px;
        border-left-color: rgba(255, 255, 255, 0.2);
        border-left-style: solid;
        border-left-width: 1px;
        border-image-source: initial;
        border-image-slice: initial;
        border-image-width: initial;
        border-image-outset: initial;
        border-image-repeat: initial;
    }


    .card_btn {
        color: white;
        text-transform: none;
    }

    .process_title {
        margin-bottom: 15px;
        font-size: 20px;
        font-weight: 100;
    }

    .process_description {
        color: #717C89 !important;
        margin-bottom: 15px;
    }


    .blockquote_author {
        color: #A8A8A8;
    }

    .blockquote_title {
        font-style: italic;
        font-size: 17px;
        line-height: 1.5;
        margin-bottom: 20px;
        margin-top: 20px;
        color: #444444;
    }

    .question_title {
        font-size: 15px !important;
        line-height: 4 !important;
        color: #444444;
        max-height: 60px;
        overflow: hidden;
        -webkit-transition: all 0.8s;
        -moz-transition: all 0.8s;
        -ms-transition: all 0.8s;
        -o-transition: all 0.8s;
        transition: all 0.8s;
    }

    .triggered_question {
        border-left: 2px solid #FF6600;
        color: #FF6600 !important;
        max-height: 400px !important;

    }

    .expand_answer {
        line-height: 1 !important;
        padding-bottom: 20px;
        text-align: justify;
    }

    .exit_icon {
        position: absolute;
        font-size: 2vw;
        right: 2vw;
        padding-top: 0.5vw;
    }

    .about_logo {
        width: 219px;
        margin-right: 22px;
        float: left;
    }

    .review_img {
        border-radius: 50%;
        width: 50%;
    }
</style>