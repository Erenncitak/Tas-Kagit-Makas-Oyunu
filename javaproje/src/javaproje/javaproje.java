package javaproje;

import java.util.Scanner;
import java.util.Random;

public class javaproje {
    public static void main(String[] args) {
    	// Kullanıcıdan veri almak için scanner'ı kullanıyorum.
        Scanner scanner = new Scanner(System.in);
        // Bilgisayarın seçimi için random kullanıyorum.
        Random random = new Random();
        
        boolean devam = true;
        
        // oyun sonunda kullanıcı "hayır" girene kadar aşağıdaki kod bloğunu çalıştır.
        while (devam) {
        	// 1.oyuncunun ismini alıp oyuncu1Adi değişkeninde tutuyorum.
            System.out.println("1. Oyuncu ismini giriniz: ");
            String oyuncu1Adi = scanner.nextLine();
         // 2.oyuncunun ismini alıp oyuncu2Adi değişkeninde tutuyorum.
            System.out.println("2. Oyuncu ismini giriniz: ");
            String oyuncu2Adi = scanner.nextLine();
            
            int toplamTur = 0;
            
            do {
            	// Kullanıcıdan oynayacak tur sayısını istiyorum ve toplamTur değişkenine atama yaparak orada tutuyorum.
                System.out.println("Toplam tur sayısını giriniz (1-10 arasında): ");
                toplamTur = scanner.nextInt();
                // Girilen sayı 1'den küçük veya 10'dan büyük olduğu sürece kod bloğunu çalıştır.
            } while (toplamTur < 1 || toplamTur > 10);
            
            //Oyuncuların puanlarını programıma tanımlıyorum.
            int oyuncu1Puan = 0;
            int oyuncu2Puan = 0;
            // turu 1 den başlatarak kullanıcıdan aldığım toplamTur değişkenine kadar 1-1 arttırarak döngüye sokuyorum.
            for (int tur = 1; tur <= toplamTur; tur++) {
            	// Oyunculardan seçim almak için paremetreli ve göri döndüren bir fonksiyonu aşağıda tanımlamıştım şimdi ise burada girilen bilgileri ona aktararak oyuncunun seçimini bir değişkene atıyorum.
                int oyuncu1Secim = oyuncuSecimAl(scanner, oyuncu1Adi);
                int oyuncu2Secim = oyuncuSecimAl(scanner, oyuncu2Adi);
                // Bilgisayara 1 ile 3 arasında bir random sayı atama işlemi yapıp bilgisayara seçim yaptırıyorum seçimler aşağıda belirtilmiştir.
                int bilgisayarSecim = random.nextInt(3) + 1; // 1: Taş, 2: Kağıt, 3: Makas
                
                // sonucHesapla adında parametreli ve geri döndürme işlemi yapan bir fonksiyon tanımlamıştım aşağı tarafta oradaki fonksiyona oyuncunun seçimini ve bilgisayarım seçimini parametre olarak gönderip kimin kazandığını öğreniyorum.
                int sonuc1 = sonucHesapla(oyuncu1Secim, bilgisayarSecim);
                int sonuc2 = sonucHesapla(oyuncu2Secim, bilgisayarSecim);
                
                // Eğer fonksiyonun döndürdüğü değer 1 ise oyuncu 1'in puanını arttırıyorum ve turu kazandığını belirtiyorum.
                if (sonuc1 == 1) {
                    oyuncu1Puan++;
                    System.out.println(oyuncu1Adi + " bu turu kazandı!");
                } 
                //Eğer fonksiyonun döndürdüğü değer 2 ise oyuncu 1'e turu kaybettin çıktısı sunuyorum.
                else if (sonuc1 == 2) {
                    System.out.println(oyuncu1Adi + " bu turu kaybetti!");
                }
                // Eğer 1 veya 2 döndürmüyorsa mecburen 3 döndürmek zorunda kalıyor (burayı else if (sonuc1==3){} olarakta yazılabilir.) ve 3 te beraberliği temsil ettiğinden tur berabere çıktısı veriyor.
                else {
                    System.out.println("Bu tur berabere!");
                }
                // Burada yapılan işlem yukarıda yapılan işlemin tamamen aynısı yukarıda 1.oyuncunun puanını hesaplamıştık burada ise 2.oyuncunun puanını hesaplıyoruz.
                if (sonuc2 == 1) {
                    oyuncu2Puan++;
                    System.out.println(oyuncu2Adi + " bu turu kazandı!");
                } else if (sonuc2 == 2) {
                    System.out.println(oyuncu2Adi + " bu turu kaybetti!");
                } else {
                    System.out.println("Bu tur berabere!");
                }
            }
            // Burada 1. ve 2. oyuncuların adlarını ve kazandıkları puanı ekrana yansıtıyorum
            System.out.println("Toplam Sonuçlar:");
            System.out.println(oyuncu1Adi + ": " + oyuncu1Puan + " puan");
            System.out.println(oyuncu2Adi + ": " + oyuncu2Puan + " puan");
            // Yukarıda hesaplanan puanlar ile oyunu kimin kazandığını bir if yapısı içerisinde karşılaştırma yaparak karar veriyorum.
            if (oyuncu1Puan > oyuncu2Puan) {
                System.out.println(oyuncu1Adi + " kazandı!");
            } else if (oyuncu2Puan > oyuncu1Puan) {
                System.out.println(oyuncu2Adi + " kazandı!");
            } else {
                System.out.println("Oyun berabere!");
            }
            // Kullanıcıya oyuna devam edip etmemek istediğini soruyorum.
            System.out.println("Yeni bir oyun oynamak istiyor musunuz? (evet/hayır): ");
            // Aşağıda kullanıcının girdiği değeri veri tipi String olan olan devamSecimi adlı değişkene atıyorum.
            scanner.nextLine(); // Önceki newline karakterini temizleme işlemi yapıyorum.
            String devamSecimi = scanner.nextLine();
            // Eğer girdiği cevap "evet" değil ise yani "hayır" ise yukarıda tanımlamış olduğum devam değişkeninin değerini false yaparak while(devam) döngüsünden çıkartıyorum
            if (!devamSecimi.equalsIgnoreCase("evet")) {
                devam = false;
            }
        }
        
        scanner.close();
    }
    
    // Oyuncunun seçimini her tur alacağımız için bunu bir fonksiyon haline getirip kodda kalabalık yapmamasını sağlıyorum ve girdiği parametrelere göre seçimini belirtip değişkende tutuyorum.
    public static int oyuncuSecimAl(Scanner scanner, String oyuncuAdi) {
        int secim;
        do {
            System.out.println(oyuncuAdi + ", seçiminizi yapınız (1: Taş, 2: Kağıt, 3: Makas): ");
            secim = scanner.nextInt();
        } while (secim < 1 || secim > 3);
        return secim;
    }
    
    // Oyuncunun puanlarını hesaplamak için bir fonksiyon oluşturuyorum ve 1.oyuncunun seçimi ile random kullanarak elde ettiğim sayıyı burada parametreye atayarak oyuncunun mu yoksa bilgisayarın mı kazandığını öğreniyorum.
    public static int sonucHesapla(int oyuncuSecim, int bilgisayarSecim) {
        if (oyuncuSecim == bilgisayarSecim) {
            return 0; // Berabere
        } else if ((oyuncuSecim == 1 && bilgisayarSecim == 3) ||
                   (oyuncuSecim == 2 && bilgisayarSecim == 1) ||
                   (oyuncuSecim == 3 && bilgisayarSecim == 2)) {
            return 1; // Oyuncu kazandı
        } else {
            return 2; // Oyuncu kaybetti
        }
    }
}
