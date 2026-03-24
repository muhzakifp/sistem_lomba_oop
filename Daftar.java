public class Daftar
{
    private Mahasiswa mhs;
    private Lomba lmb;
    private String idPendaftar;
    private String tanggalDaftar;
    private String status;

    public Daftar (Mahasiswa mhs, Lomba lmb, String iddaftar, String tgldaftar)
    {
        this.mhs = mhs;
        this.lmb = lmb;
        this.idPendaftar = "DF-"+iddaftar+"-LMB";
        this.tanggalDaftar = tgldaftar;
        this.status = "";
    }

    // ini getter
    public String getiddaftar() {return idPendaftar;}
    public String gettgldaftar() {return tanggalDaftar;}
    public String getstatus() {return status;}
    public Mahasiswa getclassMahasiswa() {return mhs;}
    public Lomba getclassLomba() {return lmb;}


    public void prosesPendaftaran(Lomba l)
    {
        System.out.println("Memproses pendaftaran untukk ID: "+idPendaftar);
        if(l.cekKuota())
        {
            l.tmbhPendaftar();
            status = "Belum disetujui";
            System.out.println("Pendaftaran berhasil diproses !");
        }
        else
        {
            status = "Gagal - Kuota Penuh !";
        }
    }
    public static void syaratDaftar()
    {
        System.out.println("1. Mahasiswa Aktif di PTN/PTS");
        System.out.println("2. Sehat jasmani dan rohani");
        System.out.println("3. Semester 1-4");
        System.out.println("4. Dapat izin dari dosen wali");
    }

    public void updateStatus(String status_baru)
    {
        this.status = status_baru;
        System.out.println("Status peserta pendaftaran atas nama "+mhs.getNama()+" telah "+status);   
    }

    public void tampilBuktiDaftar()
    {
        System.out.println("---> BUKTI PENDAFTARAN "+mhs.getNama().toUpperCase()+" <---");
        System.out.println("ID pendaftar :"+idPendaftar);
        System.out.println("Nama Mahasiswa : "+mhs.getNama());
        System.out.println("Prodi : "+mhs.getProdi());
        System.out.println("Nomor HP : "+mhs.getNohp());
        System.out.println("Email : "+mhs.getEmail());
        System.out.println("Tanggal Daftar :"+tanggalDaftar);
        System.out.println("Status :"+status);
    }

}