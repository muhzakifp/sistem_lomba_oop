public class Daftar
{
    private String idPendaftar;
    private String tanggalDaftar;
    private String status;
    private String syaratlomba;

    public Daftar (String iddaftar, String tgldaftar)
    {
        this.idPendaftar = iddaftar;
        this.tanggalDaftar = tgldaftar;
        this.status = "Menunggu verifikasi";
    }

    // ini getter
    public String getiddaftar() {return idPendaftar;}
    public String gettgldaftar() {return tanggalDaftar;}
    public String getstatus() {return status;}
    public String getsyarat() {return syaratlomba;}


    public void prosesPendaftaran(Lomba l)
    {
        System.out.println("Memproses pendaftaran untukk ID: "+idPendaftar);
        if(l.cekKuota())
        {
            l.tmbhPendaftar();
            status = "Terverifikasi";
            System.out.println("Pendaftaran berhasil diproses !");
        }
        else
        {
            status = "Gagal - Kuota Penuh !";
        }
    }
    public void syaratDaftar()
    {
        this.syaratlomba = "1 Mahasiswa Aktif di PTN/PTS | 2. Sehat |3. Semester 1-4 |4. Dapat izin dari dosen wali";
        System.out.println("Syarat Pendaftaran untuk mengikuti lomba yaitu "+syaratlomba);
    }

    public void updateStatus(String status_baru)
    {
        this.status = status_baru;
        System.out.println("Status pendaftaran "+status);   
    }

    public void tampilBuktiDaftar()
    {
        System.out.println("---> BUKTI PENDAFTARAN <---");
        System.out.println("ID pendaftar :"+idPendaftar);
        System.out.println("Tanggal Daftar :"+tanggalDaftar);
        System.out.println("Status :"+status);
    }

}