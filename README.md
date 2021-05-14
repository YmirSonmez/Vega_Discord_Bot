# VegaBot
Java öğrenirken yaptığım botlardan birisidir VegaBot. Tamamlanmadı.
[JDA Kütüphanesi](https://github.com/DV8FromTheWorld/JDA) kullanarak [Discord](https://discord.com/) için tasarlandı.



Zaman geçtikçe öğrendiğim yeni şeyler ile geliştirmeye çalıştım.

### Özellikler

Komutları  | Bilgi 
------------- | ------------- 
SetPrefix  | Sunucu için prefixi değiştirir. 
Demirci  | Kazma üretir.
Fırın  | Cevherleri külçeye çevirir. 
Item  | Eşya bilgilerini gösterir.
Envanter  | Oyuncunun envanterini ve bilgilerini gösterir.
Meslek | Meslek özellikleri gösterir. Meslek seçer.
Kaz | Kazı yapar.
Kazma | Kazmaları yönetmeyi sağlar. (Gönder,Sil,Kullan)
Sat | Madenleri satmaya yarar.(Toplu yada seçerek)
ParaGönder | Oyuncuların para transferi yapmasını sağlar.
Gönder | Oyuncuların kaynak transferini yapmasını sağlar.
Başla | Yeni bir oyuncu profili oluşturur.
YazıTura | %50 ihtimalle yatırılan para 2'ye katlanır.
Tahmin | Yatırılan para 1.5'e katlanır.
Dövüş | Şansa bağlı dövüş oyunu. (2 Kişilik paralı)

Meslek  | Kazma Slotu | Bilgi 
------------- | ------------- | -------------
İşsiz | 1 | Yeni başlayanların sahip olduğu meslek.
Demirci | 4 | Kazma üretimi yapabilen meslek.
Fırıncı | 2 | Cevherleri külçe yapabilen meslek.
Madenci | 2 | Özellik eklenmedi.

Madenler | Id | Alış F. | Satış F.  | Seviye  | Şans  | Üretim E.  | Eritildiğinde
------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | -------------
Çöp | 0 | -1 | 0 | 0 | 0 | - | null
Kırıktaş | 1 | 5 | 1 | 0 | 15 | + | Taş
Taş | 2 | 5 | 2 | 0 | 0 | - | Çöp
Kömür | 3 | 100 | 1 | 10 | 1 | - | Çöp
Demir Cevheri | 4 | 50 | 5 | 20 | 6 | - | Demir
Demir | 5 | -1 | 10 | 1 | 0 | + | Çöp
ALtın Cevheri | 6 | 500 | 25 | 40 |5 | - | Altın
Altın | 7 | -1 | 50 | 2 | 0 | + | Çöp
Elmas Cevheri | 8 | 100 | 50 | 60 | 5 | - | Elmas
Elmas | 9 | -1 | 75 | 3 | 0 | + | Çöp
Zümrüt | 10 | -1 | -1 | 100 | 1 | - | Çöp

Şansı 0 olanlar kazıda çıkmaz. -1 değeri o işlevin olmadığını belirtir (Alınamaz-Satılamaz). Üretim E. üretimde kullanılabilirliğini gösterir.
 
 # İletişim : YmirSG#5599
