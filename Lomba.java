 class Lomba
{
    private String idLomba, namaLomba, bidang, tanggalLomba;
    private int kuota, jumlahPendaftar;

    Lomba(String idLomba, String namalomba, String bidang, String tgllomba, int kuota)
    {
        this.idLomba = idLomba;
        this.namaLomba = namalomba;
        this.bidang = bidang;
        this.tanggalLomba = tgllomba;
        this.kuota = kuota;
        this.jumlahPendaftar = 0;
    }

    // ini getter
    String getId() {return idLomba;}  String getNamaLomba() {return namaLomba;}
    String getBidang() {return bidang ;} String getTanggal() {return tanggalLomba;}
    int getKuota() {return kuota;}  int getJmlhDaftar() {return jumlahPendaftar;}

    // method 1 untuk mengatur kondisi jumlah pendaftar dan kuota 
    boolean cekKuota() { return jumlahPendaftar < kuota;}

    // method 2 untuk menambah jumlah pendaftaran lomba
    void tmbhPendaftar()
    {
        if (cekKuota()) // untuk ngecek kondisi cek kuota, dan ini berhubungan dengan method 1
        {
            jumlahPendaftar++; 
            System.out.println("Pendaftaran lomba "+namaLomba+" berhasil, sisa kuota "+(kuota-jumlahPendaftar)+"!");
        }
        // jika jumlah pendaftar lebih besar daripada kuota maka kondisi akan ke else
        else { System.out.println("Maaf, kuota lomba "+namaLomba+" udah penuh ! ");}
    }

    // method 3 untuk mengurangi jumlah pendaftar
    void kurangiDaftar()
    {
        if (jumlahPendaftar > 0)
        {
            jumlahPendaftar--;
            System.out.println("Pendaftar : "+jumlahPendaftar);
        }
        else { System.out.println("Pendaftar masih kosong ! ");}
    }
    
    //method 4 untuk menampilkan informasi pendaftar
    void tampilInformasi()
    {
        System.out.println("ID Lomba :  "+idLomba);
        System.out.println("Nama Lomba :  "+namaLomba);
        System.out.println("Bidang :  "+bidang);
        System.out.println("Tanggal :  "+tanggalLomba);
        System.out.println("Pendaftar :"+jumlahPendaftar);
        System.out.println("Kuota : "+(kuota-jumlahPendaftar));
    }
}