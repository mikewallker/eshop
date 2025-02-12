# **Reflection 1**
- Dari tutorial pertama ini tentang clean coding, saya belajar untuk membuat kode yang tidak hanya berjalan dengan baik, tetapi juga bersih. Artinya bisa dibaca dan dipahami dengan mudah oleh orang lain. Di kode saya ada beberapa dokumentasi yang redundant, kode yang seharusnya sudah jelas fungsinyadiberikan komentar tambahan yang tidak perlu. Untuk penamaan variabel, saya merasa sudah cukup baik mengimplementasikannya selama ini, dengan mengingat kembalipenamaan yang baik adalah penamaan yang tidak memerlukan comment tambahan untuk penjelasan. Poin lain yang saya sadari adalah bagaimana cara handle suatu bariskode yang terlalu panjang, saya belum tahu seberapa panjang batas kode dalam 1 baris sebelum pindah ke baris selanjutnya.

# **Reflection 2**
1. Setelah membuat unit test, saya merasa aplikasi saya akan menjadi lebih mudah untuk dilakukan testing selanjutnya. Saya mempunyai opsi lain untuk testing selain melakukan input manual di web. Banyaknya unit test yang diperlukan bisa mengacu pada code coverage, sehingga semakin tinggi code coverage, semakin baik unit test karena mencakup implementasi yang lebih luas. Namun walaupun code coverage sudah 100 persen, bisa saja tetap ada bug yang disebabkan oleh kesalahan perancangan desain. Misalnya logic yang kurang tepat walaupun kode bisa dijalankan.

2. Akan muncul beberapa isu tentang clean code, serta menurunkan kualitas kode, diantaranya:
    - Duplikasi kode yang menyebabkan maintanance lebih sulit karena perubahan terhadap suatu kode harus diterapkan ke kode lainnya juga.
    - Lack of reusability, yaitu kode yang sama tidak bisa dipakai berulang, perlu tulis ulang manual
    - Kode sulit dibaca karena setiap bagian yang sama dimasukkan ulang di setiap file tes, membuat cognitive load dari programmer bertambah
      Solusinya adalah:
    - Membuat 1 test class base sehingga satu buah setup bisa digunakan untuk banyak fungsionalitas, test yang baru hanya perlu membuat class baru, tidak usah membuat file baru
    - Membuat helper class yang menyimpan baris kode yang akan dipakai berulang, sehingga tinggal memamnggil instance method dari class tersebut saja.
    - Jika ada banyak tes yang bervariasi hanya input datanya saja, gunakan test method yang memiliki parameter.
