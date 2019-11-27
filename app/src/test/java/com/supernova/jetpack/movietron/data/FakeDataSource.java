package com.supernova.jetpack.movietron.data;

import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.data.source.remote.response.MovieResponse;
import com.supernova.jetpack.movietron.data.source.remote.response.TvResponse;

import java.util.ArrayList;
import java.util.List;

public class FakeDataSource {

    public static List<ItemEntity> generateMovieItems() {
        List<ItemEntity> items = new ArrayList<>();

        items.add(new ItemEntity(332562,
                "movie",
                "A Star Is Born",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "2018-10-03",
                "https://image.tmdb.org/t/p/w154/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "https://image.tmdb.org/t/p/w780/mnDvPokXpvsdPcWSjNRPhiiLOKu.jpg"));

        items.add(new ItemEntity(297802,
                "movie",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2018-12-07",
                "https://image.tmdb.org/t/p/w154/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
                "https://image.tmdb.org/t/p/w780/9QusGjxcYvfPD1THg6oW3RLeNn7.jpg"));

        items.add(new ItemEntity(299536,
                "movie",
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "2018-04-25",
                "https://image.tmdb.org/t/p/w154/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                "https://image.tmdb.org/t/p/w780/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"));

        items.add(new ItemEntity(399579,
                "movie",
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "2019-01-31",
                "https://image.tmdb.org/t/p/w154/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "https://image.tmdb.org/t/p/w780/8yjqnpOfuFQg3qLRl9Ca1NgIBB5.jpg"));

        items.add(new ItemEntity(424694,
                "movie",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "2018-10-24",
                "https://image.tmdb.org/t/p/w154/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "https://image.tmdb.org/t/p/w780/93xA62uLd5CwMOAs37eQ7vPc1iV.jpg"));

        items.add(new ItemEntity(375588,
                "movie",
                "Robin Hood",
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "2018-11-21",
                "https://image.tmdb.org/t/p/w154/AiRfixFcfTkNbn2A73qVJPlpkUo.jpg",
                "https://image.tmdb.org/t/p/w780/axjFiijtxdfK3CD9dMwrdgLwblD.jpg"));

        items.add(new ItemEntity(480530,
                "movie",
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "2018-11-21",
                "https://image.tmdb.org/t/p/w154/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
                "https://image.tmdb.org/t/p/w780/hZqx2JcZVjHSY2lMEMDC0XlObiw.jpg"));

        items.add(new ItemEntity(452832,
                "movie",
                "Serenity",
                "Baker Dill is a fishing boat captain leading tours off a tranquil, tropical enclave called Plymouth Island. His quiet life is shattered, however, when his ex-wife Karen tracks him down with a desperate plea for help.",
                "2019-01-24",
                "https://image.tmdb.org/t/p/w154/hgWAcic93phg4DOuQ8NrsgQWiqu.jpg",
                "https://image.tmdb.org/t/p/w780/ridcUDnFumpMB5AAsIvFafTSx5i.jpg"));

        items.add(new ItemEntity(450465,
                "movie",
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "2019-01-16",
                "https://image.tmdb.org/t/p/w154/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
                "https://image.tmdb.org/t/p/w780/ngBFDOsx13sFXiMweDoL54XYknR.jpg"));

        items.add(new ItemEntity(428078,
                "movie",
                "Mortal Engines",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "2018-11-27",
                "https://image.tmdb.org/t/p/w154/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg",
                "https://image.tmdb.org/t/p/w780/rm2oMykm5nX6SzXFr7TGHkO6r8Z.jpg"));

        return items;
    }

    public static List<ItemEntity> generateTvItems() {
        List<ItemEntity> items = new ArrayList<>();

        items.add(new ItemEntity(456,
                "tv",
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "1989-12-17",
                "https://image.tmdb.org/t/p/w154/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
                "https://image.tmdb.org/t/p/w780/f5uNbUC76oowt5mt5J9QlqrIYQ6.jpg"));

        items.add(new ItemEntity(1434,
                "tv",
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "1999-01-31",
                "https://image.tmdb.org/t/p/w154/gBGUL1UTUNmdRQT8gA1LUV4yg39.jpg",
                "https://image.tmdb.org/t/p/w780/pH38r4TWTqq7Mcs6XAlwgzNUeJe.jpg"));

        items.add(new ItemEntity(12609,
                "tv",
                "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure.",
                "1986-02-26",
                "https://image.tmdb.org/t/p/w154/3wx3EAMtqnbSLhGG8NrqXriCUIQ.jpg",
                "https://image.tmdb.org/t/p/w780/iflq7ZJfso6WC7gk9l1tD3unWK.jpg"));

        items.add(new ItemEntity(46261,
                "tv",
                "Fairy Tail",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "2009-10-12",
                "https://image.tmdb.org/t/p/w154/58GKcwFV3lpVOGzybeMrrNOjHpz.jpg",
                "https://image.tmdb.org/t/p/w780/m2lugAG39sO0yCcuxEAu4fY5u1o.jpg"));

        items.add(new ItemEntity(62688,
                "tv",
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "2015-10-26",
                "https://image.tmdb.org/t/p/w154/4ka8vAzAFUZFKxWyfGfwVcSXuZo.jpg",
                "https://image.tmdb.org/t/p/w780/3x1lVyxtsLKsZyR2E3qRe1EAXG4.jpg"));

        items.add(new ItemEntity(1412,
                "tv",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "2012-10-10",
                "https://image.tmdb.org/t/p/w154/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "https://image.tmdb.org/t/p/w780/dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg"));

        items.add(new ItemEntity(79501,
                "tv",
                "Doom Patrol",
                "The Doom Patrol's members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "2019-02-15",
                "https://image.tmdb.org/t/p/w154/nVN7Dt0Xr78gnJepRsRLaLYklbY.jpg",
                "https://image.tmdb.org/t/p/w780/sAzw6I1G9JUxm86KokIDdQeWtaq.jpg"));

        items.add(new ItemEntity(54155,
                "tv",
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "2019-03-28",
                "https://image.tmdb.org/t/p/w154/5nSSkcM3TgpllZ7yTyBOQEgAX36.jpg",
                "https://image.tmdb.org/t/p/w780/oUuyMvdBIZiJHEGtKYrIZO7hyd7.jpg"));

        items.add(new ItemEntity(34307,
                "tv",
                "Shameless",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                "2011-01-09",
                "https://image.tmdb.org/t/p/w154/m2gf7SYOq9z30Q1dJFMF51DfrmF.jpg",
                "https://image.tmdb.org/t/p/w780/1ccgQ6IJyEc8UHPtGeFFeRqMVnc.jpg"));

        items.add(new ItemEntity(4614,
                "tv",
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "2003-09-23",
                "https://image.tmdb.org/t/p/w154/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg",
                "https://image.tmdb.org/t/p/w780/ms8XxpJwTPYaUcbwhO2kJS6SGVM.jpg"));

        return items;
    }

    public static List<MovieResponse> generateMovieResponse() {
        List<MovieResponse> items = new ArrayList<>();

        items.add(new MovieResponse(332562,
                "A Star Is Born",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "2018-10-03",
                "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "/mnDvPokXpvsdPcWSjNRPhiiLOKu.jpg"));

        items.add(new MovieResponse(297802,
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2018-12-07",
                "/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
                "/9QusGjxcYvfPD1THg6oW3RLeNn7.jpg"));

        items.add(new MovieResponse(299536,
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "2018-04-25",
                "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                "/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"));

        items.add(new MovieResponse(399579,
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "2019-01-31",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "/8yjqnpOfuFQg3qLRl9Ca1NgIBB5.jpg"));

        items.add(new MovieResponse(424694,
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "2018-10-24",
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "/93xA62uLd5CwMOAs37eQ7vPc1iV.jpg"));

        items.add(new MovieResponse(375588,
                "Robin Hood",
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "2018-11-21",
                "/AiRfixFcfTkNbn2A73qVJPlpkUo.jpg",
                "/axjFiijtxdfK3CD9dMwrdgLwblD.jpg"));

        items.add(new MovieResponse(480530,
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "2018-11-21",
                "/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
                "/hZqx2JcZVjHSY2lMEMDC0XlObiw.jpg"));

        items.add(new MovieResponse(452832,
                "Serenity",
                "Baker Dill is a fishing boat captain leading tours off a tranquil, tropical enclave called Plymouth Island. His quiet life is shattered, however, when his ex-wife Karen tracks him down with a desperate plea for help.",
                "2019-01-24",
                "/hgWAcic93phg4DOuQ8NrsgQWiqu.jpg",
                "/ridcUDnFumpMB5AAsIvFafTSx5i.jpg"));

        items.add(new MovieResponse(450465,
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "2019-01-16",
                "/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
                "/ngBFDOsx13sFXiMweDoL54XYknR.jpg"));

        items.add(new MovieResponse(428078,
                "Mortal Engines",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "2018-11-27",
                "/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg",
                "/rm2oMykm5nX6SzXFr7TGHkO6r8Z.jpg"));

        return items;
    }

    public static List<TvResponse> generateTvResponse() {
        List<TvResponse> items = new ArrayList<>();

        items.add(new TvResponse(456,
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "1989-12-17",
                "/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
                "/f5uNbUC76oowt5mt5J9QlqrIYQ6.jpg"));

        items.add(new TvResponse(1434,
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "1999-01-31",
                "/gBGUL1UTUNmdRQT8gA1LUV4yg39.jpg",
                "/pH38r4TWTqq7Mcs6XAlwgzNUeJe.jpg"));

        items.add(new TvResponse(12609,
                "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure.",
                "1986-02-26",
                "/3wx3EAMtqnbSLhGG8NrqXriCUIQ.jpg",
                "/iflq7ZJfso6WC7gk9l1tD3unWK.jpg"));

        items.add(new TvResponse(46261,
                "Fairy Tail",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "2009-10-12",
                "/58GKcwFV3lpVOGzybeMrrNOjHpz.jpg",
                "/m2lugAG39sO0yCcuxEAu4fY5u1o.jpg"));

        items.add(new TvResponse(62688,
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "2015-10-26",
                "/4ka8vAzAFUZFKxWyfGfwVcSXuZo.jpg",
                "/3x1lVyxtsLKsZyR2E3qRe1EAXG4.jpg"));

        items.add(new TvResponse(1412,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "2012-10-10",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "/dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg"));

        items.add(new TvResponse(79501,
                "Doom Patrol",
                "The Doom Patrol's members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "2019-02-15",
                "/nVN7Dt0Xr78gnJepRsRLaLYklbY.jpg",
                "/sAzw6I1G9JUxm86KokIDdQeWtaq.jpg"));

        items.add(new TvResponse(54155,
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "2019-03-28",
                "/5nSSkcM3TgpllZ7yTyBOQEgAX36.jpg",
                "/oUuyMvdBIZiJHEGtKYrIZO7hyd7.jpg"));

        items.add(new TvResponse(34307,
                "Shameless",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                "2011-01-09",
                "/m2gf7SYOq9z30Q1dJFMF51DfrmF.jpg",
                "/1ccgQ6IJyEc8UHPtGeFFeRqMVnc.jpg"));

        items.add(new TvResponse(4614,
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "2003-09-23",
                "/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg",
                "/ms8XxpJwTPYaUcbwhO2kJS6SGVM.jpg"));

        return items;
    }
}
