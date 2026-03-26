class Mahasiswa 
{
    private String nim;
    private String nama;
    private String prodi;   // PENGGUNAAN KONSEP ENKAPSULASI (atribut di kasih private)
    private int semester;
    private String kampus;
    private String nohp;

    Mahasiswa(String nim, String nama, String prodi, int smstr, String kampus, String nohp)
    {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.semester = smstr;
        this.kampus = kampus;
        this.nohp = nohp;
    }
    // ini getter
    String getNim(){return nim;}
    String getNama(){return nama;}
    String getProdi(){return prodi;}   //  METHOD GETTER SEBAGAI JEMBATAN AKSES UNTUK MENAMPILKAN HASIL ATRIBUT YANG DI ENKAPSULASI
    int getSmstr(){return semester;}  
    String getKampusAsal(){return kampus;}
    String getNohp(){return nohp;}

    // ini method 1 untuk menampikan seluruh data mahasiswa yang telah dibuat
    void getDataMahasiwa()
    {
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("---> DATA MAHASISWA <---");
        System.out.println("NIM : "+nim);
        System.out.println("Nama : "+nama);
        System.out.println("Prodi : "+prodi);
        System.out.println("Semester : "+semester);
        System.out.println("Email : "+kampus);
        System.out.println("No HP : "+nohp);
        System.out.println("-----------------------------------------------------------------");
    }

}

