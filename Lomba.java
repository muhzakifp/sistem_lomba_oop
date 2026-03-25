 class Lomba
{
    protected String idLomba;
    protected String namaLomba;
    protected String bidang;
    private String tanggalLomba;
    private int kuota ;
    private int jumlahPendaftar;

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
    String getId() {return idLomba;}
    String getNamaLomba() {return namaLomba;}
    String getBidang() {return bidang ;}
    String getTanggal() {return tanggalLomba;}
    int getKuota() {return kuota;}
    int getJmlhDaftar() {return jumlahPendaftar;}

    // method 1
    boolean cekKuota()
    {
        return jumlahPendaftar < kuota;
    }

    // method 2
    void tmbhPendaftar()
    {
        if (cekKuota())
        {
            jumlahPendaftar++;
            System.out.println("Pendaftaran lomba berhasil, sisa kuota "+(kuota-jumlahPendaftar)+"!");
        }
        else
        {
            System.out.println("Maaf, kuota sudah penuh ! ");
        }
    }

    // method 3
    void kurangiDaftar()
    {
        if (jumlahPendaftar > 0)
        {
            jumlahPendaftar--;
            System.out.println("Pendaftar : "+jumlahPendaftar);
        }
        else
        {
            System.out.println("Pendaftar masih kosong ! ");
        }
    }
    
    //method 4
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