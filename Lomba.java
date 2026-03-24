 class Lomba
{
    private String idLomba;
    private String namaLomba;
    private String bidang;
    private String tanggalLomba;
    private int kuota ;
    private int jumlahPendaftar;

    public Lomba(String idLomba, String namalomba, String bidang, String tgllomba, int kuota)
    {
        this.idLomba = idLomba;
        this.namaLomba = namalomba;
        this.bidang = bidang;
        this.tanggalLomba = tgllomba;
        this.kuota = kuota;
        this.jumlahPendaftar = 0;
    }

    // ini getter
    public String getId() {return idLomba;}
    public String getNamaLomba() {return namaLomba;}
    public String getBidang() {return bidang ;}
    public String getTanggal() {return tanggalLomba;}
    public int getKuota() {return kuota;}
    public int getJmlhDaftar() {return jumlahPendaftar;}

    // method 1
    public boolean cekKuota()
    {
        return jumlahPendaftar < kuota;
    }

    // method 2
    public void tmbhPendaftar()
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
    public void tampilInformasi()
    {
        System.out.println("ID Lomba :  "+idLomba);
        System.out.println("Nama Lomba :  "+namaLomba);
        System.out.println("Bidang :  "+bidang);
        System.out.println("Tanggal :  "+tanggalLomba);
        System.out.println("Pendaftar :"+jumlahPendaftar);
        System.out.println("Kuota : "+(kuota-jumlahPendaftar));
    }
}