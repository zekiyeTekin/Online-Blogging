# Online Blogging Project

## İçindekiler
- [Proje Amacı](#proje-amacı)
- [Teknik Detaylar](#teknik-detaylar)
- [Özellikler](#özellikler)
- [İletişim](#iletişim)


## Proje Amacı

**Online Blogging**, kullanıcıların özgürce içerik paylaşabilecekleri, paylaşılan postları beğenebilecekleri ve yorum yapabilecekleri bir platformdur.

Bu proje, kullanıcıların fikirlerini, düşüncelerini ve yaratıcı içeriklerini geniş bir kitleyle paylaşmasını amaçlar.

Aynı zamanda, kullanıcı etkileşimlerini teşvik eden beğeni ve yorum sistemi ile interaktif bir ortam sunmayı hedeflemektedir.


## Teknik Detaylar
- Backend: **Spring Boot**
- Frontend: **HTML/CSS/JavaScript**
- Veritabanı: **PostgreSQL**
- Kimlik Doğrulama: **JWT Token**
- Build Tool: **Maven**
- Test : **JUnit,Mockito**
- API Dokümantasyonu: **Postman**
- **Spring Data JPA**
- **RESTful API**
- **Docker**


## Özellikler
### 1. Post Paylaşımı
- Kayıtlı kullanıcılar, blog yazılarını (post) oluşturabilir ve yayınlayabilir.
- Her post, bir başlık (name), içerik (content), yazan kişi (postedBy), ve tarih (date) bilgilerini içerir.

### 2. Beğenme (Like) ve Yorum Yapma
- **Giriş yapmış kullanıcılar**, post oluşturup, postları görebilir, beğenebilir ve yorum yapabilir.
- **Giriş yapmamış kullanıcılar**, sadece giriş sayfasını görebilirler.

### 3. Post Detayları
- Her postun **beğeni sayısı** (likeCount) ve **görüntülenme sayısı** (viewCount) tutulur.
- Detay sayfasına tıklandığında, postunun kime ait olduğu, posun oluşturulma tarihi, post içeriği, etiketleri ve posta yapılan yorumları gösterilir.
- Ana Sayfada ise tüm postlar ve o postlara ait olan, post sahibi, post oluşturulma tarihi, içerik, beğeni ve görüntülenme bilgisi gösterilir.

### 4. Yorum Sistemi
- Yalnızca giriş yapmış kullanıcılar postlara yorum yapabilir.
- Yorumlar, yorum yapan kişi, tarih ve içerik bilgilerini içerir.

### 5. Arama (Search) İşlevi
- Kullanıcılar, **post başlıklarına** göre arama yapabilir.
- Arama sonuçları, sadece ilgili postları listeler.


## İletişim
Herhangi bir sorunuz veya geri bildiriminiz varsa benimle iletişime geçebilirsiniz:

- Email: [zekiyetekin08@gmail.com](mailto:zekiyetekin08@gmail.com)
- LinkedIn: [Zekiye Tekin](https://www.linkedin.com/in/zekiyetekin)



