package com.dicoding.myklontongapps.model

object FakeKlontongDataSource {
    val dummyFoods = listOf(
        KlontongFood(
            id = 1,
            listImageUrl = listOf(
                ImageFood("https://thumb.viva.co.id/media/frontend/thumbs3/2016/05/03/5727ee73cc978-7-makanan-asli-dari-indonesia-ini-jadi-favoritnya-warga-dunia_665_374.jpg"),
                ImageFood("https://static.rctiplus.id/media/900/files/fta_rcti/news/2304989.jpg"),
                ImageFood("https://www.masakapahariini.com/wp-content/uploads/2019/06/sate-ayam-ponorogo-780x440.jpg"),
                ImageFood("https://img-global.cpcdn.com/recipes/a7d1930dca4765e0/751x532cq70/sate-ayam-madura-foto-resep-utama.jpg"),
            ),
            nameFood = "Sate Ayam",
            priceFood = 20000,
            descriptionFood = "Sate Ayam dibuat dengan daging pilihan yang dibakar dengan menggunakan arang",
            ratingFood = 4.8,
            totalAvailable = 120
        ),
        KlontongFood(
            id = 2,
            listImageUrl = listOf(
                ImageFood("https://awsimages.detik.net.id/community/media/visual/2022/06/29/gado-gado-legendaris-2.jpeg?w=1200"),
                ImageFood("https://asset-a.grid.id/crop/0x0:0x0/x/photo/2019/08/29/1093597743.jpg"),
                ImageFood("https://img-global.cpcdn.com/recipes/d673616eeeb1cbf5/1200x630cq70/photo.jpg"),
                ImageFood("https://img-global.cpcdn.com/recipes/df29e9f4c38facdc/1200x630cq70/photo.jpg"),
            ),
            nameFood = "Gado Gado",
            priceFood = 10000,
            descriptionFood = "Gado Gado dibuat dengan sayuran, gorengan, telur dan kerupuk yang ditaburi bumbu kacang",
            ratingFood = 4.7,
            totalAvailable = 120
        ),
        KlontongFood(
            id = 3,
            listImageUrl = listOf(
                ImageFood("https://aksaraintimes.id/wp-content/uploads/2022/02/Pecel-Madiun.jpg"),
                ImageFood("https://awsimages.detik.net.id/community/media/visual/2021/05/21/cara-membuat-sambal-pecel-madiun-1.jpeg?w=700&q=90"),
                ImageFood("https://i0.wp.com/img-global.cpcdn.com/recipes/51a197cb0115aa01/1360x964cq70/pecel-madiun.jpg"),
                ImageFood("https://img.inews.co.id/files/inews_new/2020/11/12/pecel_madiun_campuran_bayam.jpg"),
            ),
            nameFood = "Pecel Madiun",
            priceFood = 10000,
            descriptionFood = "Pecel Madiun adalah pecel khas madiun dengan rasa sambal yang enak dengan lauk tempe/rempeyek",
            ratingFood = 4.9,
            totalAvailable = 200
        ),
        KlontongFood(
            id = 4,
            listImageUrl = listOf(
                ImageFood("https://salahterselubung.files.wordpress.com/2019/02/whatsapp-image-2019-02-26-at-9.51.09-pm.jpeg"),
                ImageFood("https://img-global.cpcdn.com/recipes/9de6f5a7a44cacdd/1200x630cq70/photo.jpg"),
                ImageFood("https://i0.wp.com/img-global.cpcdn.com/recipes/c8412f2ebd38d141/1360x964cq70/tepo-kecap.jpg"),
                ImageFood("https://i.gojekapi.com/darkroom/gofood-indonesia/v2/images/uploads/84f2e1b0-d71d-4589-b2c6-b93a2714550c_Go-Biz_20211026_151541.jpeg?h=636&w=1082&fit=crop&auto=compress"),
            ),
            nameFood = "Tepo",
            priceFood = 5000,
            descriptionFood = "Tepo adalah makanan dengan bahan ketupat yang disajikan dengan sayuran/kecap dengan lauk tempe/krupuk",
            ratingFood = 4.6,
            totalAvailable = 60
        ),
        KlontongFood(
            id = 5,
            listImageUrl = listOf(
                ImageFood("https://cdn-2.tstatic.net/makassar/foto/bank/images/Resep-Rawon-Daging-Ini-Bikin-Sahur-Jadi-Lebih-Bersemangat.jpg"),
                ImageFood("https://cdn-2.tstatic.net/tribunnews/foto/bank/images/rawon.jpg"),
                ImageFood("https://selerasa.com/wp-content/uploads/2018/11/rawon-jogja-1200x720.jpg"),
                ImageFood("https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2022/05/04/2250899870.png"),
            ),
            nameFood = "Rawon",
            priceFood = 20000,
            descriptionFood = "Rawon adalah makanan khas jawa timur biasanya berisi daging sapi dengan bumbu rempah alami",
            ratingFood = 4.7,
            totalAvailable = 50
        ),
        KlontongFood(
            id = 6,
            listImageUrl = listOf(
                ImageFood("https://assets.pikiran-rakyat.com/crop/0x508:821x1011/x/photo/2022/04/26/2747824482.jpg"),
                ImageFood("https://grudo.ngawikab.id/wp-content/uploads/2021/05/soto-2.jpg"),
                ImageFood("https://assets.pikiran-rakyat.com/crop/0x0:0x0/750x500/photo/2022/03/04/708251878.png"),
                ImageFood("https://img.okezone.com/content/2022/05/04/298/2589270/mau-yang-segar-masak-soto-kudus-saja-yuk-dGb3ULTCkn.jpg"),
            ),
            nameFood = "Soto",
            priceFood = 10000,
            descriptionFood = "Soto adalah makanan dengan lauk daging ayam, kuah soto, dan krupuk dengan menggunakan nasi",
            ratingFood = 4.9,
            totalAvailable = 190
        ),
        KlontongFood(
            id = 7,
            listImageUrl = listOf(
                ImageFood("https://cdn0-production-images-kly.akamaized.net/fMEryffy2mhD6GQMRkwp9Xa8q4w=/1032x0:4489x3457/1200x900/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/3903607/original/012933000_1642144775-shutterstock_2091544333.jpg"),
                ImageFood("https://www.resepistimewa.com/wp-content/uploads/resep-wingko-babat.jpg"),
                ImageFood("https://blog.tokowahab.com/wp-content/uploads/2019/12/Resep-Wingko-Babat-yang-Enak.jpg"),
                ImageFood("https://i.ytimg.com/vi/j6ujXGqaPps/maxresdefault.jpg"),
            ),
            nameFood = "Wingko Babat",
            priceFood = 13000,
            descriptionFood = "Wingko Babat adalah makanan yang dibuat dari bahan seperti kelapa muda, tepung beras ketan, dan gula",
            ratingFood = 4.5,
            totalAvailable = 210
        ),
        KlontongFood(
            id = 8,
            listImageUrl = listOf(
                ImageFood("https://correcto.id/content/images/th1_2020071305321357034.jpg"),
                ImageFood("https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2020/08/16/3585052692.jpeg"),
                ImageFood("https://images.genpi.co/resize/1280x860-100/uploads/jateng/arsip/watermark/2022/04/15/es-buto-ijo-kartasura-foto-genpicodesty-luthfiani-miok.webp"),
                ImageFood("https://jurnalisindonesia.id/uploads/large/7c77547018088edb360d08cce9510e67.jpg"),
            ),
            nameFood = "Es Buto Ijo",
            priceFood = 7000,
            descriptionFood = "Es Buto Ijo adalah minuman yang berbahan dasar kelapa muda, nata de coco, kolang kaling serta sagu mutiara.",
            ratingFood = 4.7,
            totalAvailable = 300
        ),
        KlontongFood(
            id = 9,
            listImageUrl = listOf(
                ImageFood("https://awsimages.detik.net.id/community/media/visual/2020/05/14/0af32d8b-36b7-4555-8e79-4fd54c98f795.jpeg?w=700&q=90"),
                ImageFood("https://akcdn.detik.net.id/community/media/visual/2021/04/13/900209858_43.jpeg?w=250&q="),
                ImageFood("https://bisnismuda.id/assets/content/20201130123848000000IllustrationWebBisnisMuda20201130T123136001.jpg"),
                ImageFood("https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2020/10/09/922223947.jpg"),
            ),
            nameFood = "Es Teh",
            priceFood = 5000,
            descriptionFood = "Es Teh adalah minuman yang dibuat dengan gula dan menggunakan daun teh alami yang telah diolah",
            ratingFood = 4.6,
            totalAvailable = 400
        ),
        KlontongFood(
            id = 10,
            listImageUrl = listOf(
                ImageFood("https://img-global.cpcdn.com/recipes/cd3e133a9306252e/1200x630cq70/photo.jpg"),
                ImageFood("https://img-global.cpcdn.com/recipes/b9d5820b9c42e230/680x482cq70/wedang-cemue-cemoe-foto-resep-utama.jpg"),
                ImageFood("https://img-global.cpcdn.com/recipes/eb9af2b6ed0b9307/1200x630cq70/photo.jpg"),
                ImageFood("https://i0.wp.com/i.gojekapi.com/darkroom/gofood-indonesia/v2/images/uploads/839828bd-a393-41d3-b7b7-974478c76a95_IMG_20200812_231511_122.jpg"),
            ),
            nameFood = "Cemue",
            priceFood = 10000,
            descriptionFood = "Cemue adalah minuman yang dibuat dengan perpaduan santan, pandan dan jahe, dengan toping roti dan kacang yang cocok diminum hangat",
            ratingFood = 4.8,
            totalAvailable = 150
        ),
        KlontongFood(
            id = 11,
            listImageUrl = listOf(
                ImageFood("https://img-global.cpcdn.com/recipes/c0da170a95e73250/751x532cq70/wedang-ronde-foto-resep-utama.jpg"),
                ImageFood("https://cdn1-production-images-kly.akamaized.net/i_F3eCcVDeayB76d7TB9XOelGUk=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/2834701/original/013278100_1561180469-iStock-663898564.jpg"),
                ImageFood("https://www.piknikdong.com/wp-content/uploads/2021/06/Resep-Wedang-Ronde-Sederhana-min.jpg"),
                ImageFood("https://topwisata.info/wp-content/uploads/2021/02/Wedang2BRonde2B252812529.jpg"),
            ),
            nameFood = "Wedang Ronde",
            priceFood = 10000,
            descriptionFood = "Wedang Ronde adalah minuman yang dibuat dari beras ketan dan air, dibentuk bulat yang disajikan dalam kuah jahe hangat dan gula.",
            ratingFood = 4.6,
            totalAvailable = 200
        ),
        KlontongFood(
            id = 12,
            listImageUrl = listOf(
                ImageFood("https://bacaterus.com/wp-content/uploads/2018/08/Minuman-Boba-Copy.jpg"),
                ImageFood("https://cdf.orami.co.id/unsafe/cdn-cas.orami.co.id/parenting/images/boba-tea.width-800.jpegquality-80.jpg"),
                ImageFood("https://caramenjadi.com/wp-content/uploads/2022/10/Rekomendasi-Minuman-Boba.jpeg"),
                ImageFood("https://akcdn.detik.net.id/api/wm/2020/07/13/ilustrasi-boba_169.jpeg")
            ),
            nameFood = "Boba",
            priceFood = 8000,
            descriptionFood = " Boba sendiri bentuknya sama seperti pearl bubble dan sama-sama terbuat dari tepung tapioka. Namun, biasanya boba ini langsung meletus ketika masuk ketika sudah masuk ke dalam mulut.",
            ratingFood = 4.8,
            totalAvailable = 300
        ),
        KlontongFood(
            id = 13,
            listImageUrl = listOf(
                ImageFood("https://bacaterus.com/wp-content/uploads/2018/08/Cheese-Tea-Copy.jpg"),
                ImageFood("https://img-cdn.medkomtek.com/OTuXpVXIuJzXOLCwS8hlaFvldlE=/0x0/smart/filters:quality(75):strip_icc():format(webp)/article/OQHghKqyikF7-8uiU3WwY/original/059069500_1565680517-Sering-Minum-Minuman-Cheese-Tea-Bisa-Bikin-Kolesterol-Naik-By-Theerawan-Shutterstock.jpg"),
                ImageFood("https://www.gotravelly.com/blog/wp-content/uploads/2020/02/kase-cheese-tea.jpg"),
                ImageFood("https://blog-images.reddoorz.com/uploads/image/file/4735/2017-09-06-17-04-41_koi_2.jpg")
            ),
            nameFood = "Cheese Tea",
            priceFood = 10000,
            descriptionFood = "minuman yang berbahan dasar teh atau the dicampur susu dengan menambahkan krim keju lembut dalam minumannya.",
            ratingFood = 4.6,
            totalAvailable = 230
        ),
        KlontongFood(
            id = 14,
            listImageUrl = listOf(
                ImageFood("https://bacaterus.com/wp-content/uploads/2018/08/Es-Cappuccino-Cingcau-Copy.jpg"),
                ImageFood("https://www.topwisata.info/wp-content/uploads/2022/07/Es-Cappucino-Cincau-1.jpeg"),
                ImageFood("https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2021/03/10/3725666887.jpeg"),
                ImageFood("https://1.bp.blogspot.com/-nT63q1C0x7w/Xrxgex2y12I/AAAAAAAAC5s/f1BtbHdSf4I6mq2pq8h3t14Pq0X62D_VACLcBGAsYHQ/w680/Resep-es-cincau-milo.jpg")
            ),
            nameFood = "Capuccino Cincau",
            priceFood = 7000,
            descriptionFood = "Cingcau sendiri adalah minuman es tradisional yang sudah ada sejak lama, kemudian dikreasikan dengan es cappuccino yang berasal dari kopi susu.",
            ratingFood = 4.8,
            totalAvailable = 260
        ),
        KlontongFood(
            id = 15,
            listImageUrl = listOf(
                ImageFood("https://bacaterus.com/wp-content/uploads/2018/08/Alpukat-Kocok-Copy.jpg"),
                ImageFood("https://i0.wp.com/i.gojekapi.com/darkroom/gofood-indonesia/v2/images/uploads/8f19e7da-bb2a-4b05-98b4-0eb86ca25047_IMG_20201122_215041_988.jpg"),
                ImageFood("https://imgx.sonora.id/crop/0x0:0x0/700x465/photo/2020/08/06/624310969.jpg"),
                ImageFood("https://i0.wp.com/i.gojekapi.com/darkroom/gofood-indonesia/v2/images/uploads/76fbf029-2a2c-40db-8df8-5aeef26da063_Go-Biz_20201202_231305.jpeg")
            ),
            nameFood = "Alpukat Kocok",
            priceFood = 14000,
            descriptionFood = "Minuman ini dibuat dari bahan dasar buah alpukat yang dipotong dan dihaluskan kasar, kemudian ditambah dengan air kelapa, gula, susu, dan es batu.",
            ratingFood = 4.8,
            totalAvailable = 140
        ),
        KlontongFood(
            id = 16,
            listImageUrl = listOf(
                ImageFood("https://info.populix.co/wp-content/uploads/2022/04/5-8.webp"),
                ImageFood("https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2022/09/20/464700122.jpg"),
                ImageFood("https://portal.riau24.com/news/20201116/riau24_1605502645.jpeg"),
                ImageFood("https://assets.promediateknologi.com/crop/0x239:1080x1157/x/photo/2022/09/26/1877791209.jpg")
            ),
            nameFood = "Donat Crispy",
            priceFood = 10000,
            descriptionFood = "donat yang telah diisi dengan fla akan digoreng kembali setelah dilapisi dengan cairan tepung. Lapisan donut akan semakin renyah tetapi bagian dalamnya tetap lembut dan creamy.",
            ratingFood = 4.8,
            totalAvailable = 200
        ),
        KlontongFood(
            id = 17,
            listImageUrl = listOf(
                ImageFood("https://info.populix.co/wp-content/uploads/2022/04/8-1.webp"),
                ImageFood("https://www.delisari.com/uploads/news/1/untitledsda4.png"),
                ImageFood("https://asset.kompas.com/crops/j4NcjKuSniOYfBG8oC9jlwG1zpQ=/0x110:1335x1000/780x390/data/photo/2021/02/06/601e8af12c766.jpg"),
                ImageFood("https://cdn0-production-images-kly.akamaized.net/lqFY-tP0fRzdUsefV_M11aWnPsg=/0x1686:2569x3134/1200x675/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/3545908/original/099377600_1629434369-shutterstock_1764235190.jpg")
            ),
            nameFood = "Dessert box",
            priceFood = 35000,
            descriptionFood = "Bagian base bisa berupa crunchy crumb atau cake lembut dengan berbagai rasa. Kemudian untuk layer cream akan disesuaikan dengan varian yang Anda pilih. Cream dan kue akan ditumpuk hingga 2-3 lapis dan terakhir diberi toping.",
            ratingFood = 4.9,
            totalAvailable = 100
        ),
    )

    val profileUser = User(
        imageProfile = "https://media.licdn.com/dms/image/D5603AQHriVVoAgoUEQ/profile-displayphoto-shrink_800_800/0/1665584257917?e=1677110400&v=beta&t=qymb8KQJeZIDyaHPz4AAQ3NMfDv7KhHzx5xXJkbiiZM",
        imageUrl = "https://images.unsplash.com/photo-1461749280684-dccba630e2f6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=869&q=80",
        name = "Faroh Muhammad Lukas",
        description = "Halo nama saya Faroh Muhammad Lukas, Saya seorang mahasiswa dari Kota Madiun. Saya dari Teknik Informatika yang saat ini sedang mendalami programming khususnya Mobile App Android dan Flutter",
        username = "farohlukas11",
        email = "farohlukas11@gmail.com"
    )
}