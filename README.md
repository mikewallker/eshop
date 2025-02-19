# Modul 1
### **Reflection 1**
- Dari tutorial pertama ini tentang clean coding, saya belajar untuk membuat kode yang tidak hanya berjalan dengan baik, tetapi juga bersih. Artinyabisa dibaca dan dipahami dengan mudah oleh orang lain. Di kode saya ada beberapa dokumentasi yang redundant, kode yang seharusnya sudah jelas fungsinyadiberikan komentar tambahan yang tidak perlu. Untuk penamaan variabel, saya merasa sudah cukup baik mengimplementasikannya selama ini, dengan mengingat kembalipenamaan yang baik adalah penamaan yang tidak memerlukan comment tambahan untuk penjelasan. Poin lain yang saya sadari adalah bagaimana cara handle suatu bariskode yang terlalu panjang, saya belum tahu seberapa panjang batas kode dalam 1 baris sebelum pindah ke baris selanjutnya.

### **Reflection 2**
1. Setelah membuat unit test, saya merasa aplikasi saya akan menjadi lebih mudah untuk dilakukan testing selanjutnya. Saya mempunyai opsi lain untuk testing selain melakukan input manual di web. Banyaknya unit test yang diperlukan bisa mengacu pada code coverage, sehingga semakin tinggi code coverage, semakin baik unit test karena mencakup implementasi yang lebih luas. Namun walaupun code coverage sudah 100 persen, bisa saja tetap ada bug yang disebabkan oleh kesalahan perancangan desain. Misalnya logic yang kurang tepat walaupun kode bisa dijalankan.

2. Akan muncul beberapa isu tentang clean code, serta menurunkan kualitas kode, diantaranya:
   - Duplikasi kode yang menyebabkan maintanance lebih sulit karena perubahan terhadap suatu kode harus diterapkan ke kode lainnya juga.
   - Lack of reusability, yaitu kode yang sama tidak bisa dipakai berulang, perlu tulis ulang manual
   - Kode sulit dibaca karena setiap bagian yang sama dimasukkan ulang di setiap file tes, membuat cognitive load dari programmer bertambah
     Solusinya adalah:
   - Membuat 1 test class base sehingga satu buah setup bisa digunakan untuk banyak fungsionalitas, test yang baru hanya perlu membuat class baru, tidak usah membuat file baru
   - Membuat helper class yang menyimpan baris kode yang akan dipakai berulang, sehingga tinggal memamnggil instance method dari class tersebut saja.
   - Jika ada banyak tes yang bervariasi hanya input datanya saja, gunakan test method yang memiliki parameter.

# Modul 2
### **Reflection**
1. Code Quality Issues:
   - There are some empty methods that don’t have any comments. My strategy is to add comments explaining that they are there either for consistency or future use.
   - In the ProductService interface, the field is declared as public, even though interface fields are automatically public static final. To remove redundancy and improve code cleanliness, I removed the explicitly written public modifier.
   - PMD gives warnings about using utility classes, but it incorrectly identifies the main class as a potential utility class. To clean up the code analysis report, I suppressed the warning.
   - There was no top-level permission defined in the ci.yml file, so I added read-all permissions to it.
2. Yes, I believe the current implementation meets the definition of CI/CD. Continuous Integration ensures code quality before merging or deploying by running automated tests (ci.yml), performing code analysis (pmd.yml), and verifying build success. Continuous Deployment ensures automatic deployment after passing CI, which is implemented using the Koyeb GitHub App in my repository’s main branch. Since all the essential CI/CD tasks are implemented in the repository, it fulfills the definition of CI/CD.