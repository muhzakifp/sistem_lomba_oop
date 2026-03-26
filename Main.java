import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    // ARRAY LIST SETIAP CLASS
    static ArrayList<Mahasiswa> DaftarMahasiswa = new ArrayList<>(); //untuk menampung setiap data mahasiswa
    static ArrayList<Lomba> DaftarLomba = new ArrayList<>(); // untuk menampung setiap data perlombaan yang tersedia
    static ArrayList<Daftar> DaftarRegistrasi = new ArrayList<>(); // untuk memnampung data mahasiswa yang resmi jadi peserta lomba
    static ArrayList<Daftar> DataSementara = new ArrayList<>(); // untuk menampung data sementara  mahasiswa untuk menunggu pemrosesesan update status
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        // DATA PERLOMBAAN
        DaftarLomba.add(new Lomba("LO2624001", "pengembangan Website", "Teknologi", "1-Januari-2026", 20));
        DaftarLomba.add(new Lomba("LO2624002", "robotik", "Teknologi", "12-Januari-2026", 20));
        DaftarLomba.add(new Lomba("LO2624004", "Futsal", "Olahraga", "15-Februari-2026", 15));
        DaftarLomba.add(new Lomba("LO2624005", "Desain Poster", "Seni dan Kreativitas", "2-Februari-2026", 1));
        DaftarLomba.add(new Lomba("LO2624007", "Karya Ilmiah", "Penelitian", "10-Januari-2026", 5));
        DaftarLomba.add(new LombaAkademik("LO2624008", "Olimpiade Matematika", "Akademik", "12-januari-2026", 5, "Medium", "Aljabar dan Kalkulus"));

        int opsi;
        do
        {
            // SYARAT PENDAFTARAN
            System.out.println("\n! Syarat Pendaftaran !");
            Daftar.syaratDaftar();

            // FITUR MENU
            System.out.println("\n---> SISTEM PENDAFTARAN LOMBA <---");
            System.out.println("1.Tambah Mahasiswa");
            System.out.println("2.Daftarkan Lomba");
            System.out.println("3.Lihat Bukti Pendaftaran");
            System.out.println("4.Update Status Pendaftaran");
            System.out.println("5.Lihat Peserta lomba");
            System.out.println("6.Keluar");
            System.out.print("Pilih menu : ");
            opsi = input.nextInt();
            input.nextLine();

            // PERCBANGAN SWITCH
            switch (opsi)
            {
                case 1: TambahMahasiswa(); break;
                case 2: DaftarLomba(); break;
                case 3: LihatBuktiDaftar(); break;
                case 4: UpdateStatus(); break;
                case 5: LihatPesertaLomba(); break;
                case 6: System.out.println("Anda telah keluar"); break;
                default : System.out.println("Pilihan tidak valid !"); 
            }
        }while (opsi !=6);
    }

    // FUNCTION TAMBAH MAHASISWA
    public static void TambahMahasiswa()
    {
        String nim, nama, prodi,kampus,nohp;
        int smstr;

        System.out.println("\n---> DAFTARKAN MAHASISWA <---");
        System.out.print("NIM : ");
        nim = input.nextLine();
        for (Mahasiswa mhs  : DaftarMahasiswa) // untuk mengecek isi objek mhs yang terdapat pada DaftarMahasiswa
        {
            if(mhs.getNim().equals(nim)) // untuk mengecek NIM 
            {
                System.out.println("NIM sudah adaa !"); 
                return;
            }
        }
        System.out.print("Nama : "); 
        nama = input.nextLine();
        System.out.print("Prodi : ");
        prodi = input.nextLine();
        System.out.print("Semester : ");
        smstr = input.nextInt();
        input.nextLine();
        if (smstr<=0 || smstr>4 )
        {
            System.out.println("--- Semester tidak valid ! ---");
            System.out.println("--- Pendaftar wajib Mahasiswa Semester 1-4 ---");
            return;
        }
        System.out.print("Kampus Asal : ");
        kampus = input.nextLine();
        System.out.print("Nomor HP aktif : ");
        nohp = input.nextLine();

        Mahasiswa mhs = new Mahasiswa(nim, nama, prodi, smstr, kampus, nohp); // objek mhs dibuat 
        DaftarMahasiswa.add(mhs); // data objek akan tersimpan di ArrayList DaftarMahasiswa
        mhs.getDataMahasiwa(); // menampilkan data mahasiswa
        System.out.println("                 Mahasiswa berhasil ditambahkan !              ");
    }
    
    // FUNCTION DAFTAR LOMBA
    public static void DaftarLomba()
    {
        // ARRAYLIST UNTUK MENYIMPAN URUTAN LOMBA
        ArrayList<Lomba> urutan_lomba = new ArrayList<>();

        int no = 1;
        if(DaftarMahasiswa.isEmpty()) // pengecekan kondisi jika ArrayList DaftarMahasiswa kosong 
        {
            System.out.println("Belum ada data Mahasiswa ! ");
            return;
        }

        System.out.println("\n---> DAFTAR LOMBA TERSEDIA <---"); 
        System.out.println("---LOMBA UMUM---");
        for (Lomba l : DaftarLomba) 
        {
              // menampilkan urutan informasi lomba umum
            if(!(l instanceof LombaAkademik))
            {
                System.out.println((no)+"."+l.getId()+"|"+l.getNamaLomba()+"|"+l.getBidang());
                urutan_lomba.add(l);
                no++;
            }
        }
        System.out.println("---LOMBA AKADEMIK---");
        for(Lomba l : DaftarLomba)
        {
            // menampilkan urutan informasi lomba akademik
            if(l instanceof LombaAkademik)
            {
                System.out.println((no)+"."+l.getId()+"|"+l.getNamaLomba()+"|"+l.getBidang());
                urutan_lomba.add(l);
                no++;
            }
        }

        // input untuk pemilihan list lomba 
        System.out.print("Pilih index lomba untuk melihat detail informasi  : ");
        int pil_lmb =  input.nextInt();
        input.nextLine();
        
        if (pil_lmb >=1 && pil_lmb <= urutan_lomba.size())
        {

            Lomba lomba_terpilih = urutan_lomba.get(pil_lmb-1);
          
            System.out.println("\n--- INFORMASI LOMBA "+lomba_terpilih.getNamaLomba().toUpperCase() +"---");
            lomba_terpilih.tampilInformasi();
            
            System.out.print("Apakah yakin ingin mendaftar?  (Y/N) : ");
            String konf = input.nextLine();
            if (konf.equalsIgnoreCase("y"))
                {
                    // cek kondisi kuota lomba
                    if (!(lomba_terpilih.cekKuota()))
                    {
                        lomba_terpilih.tmbhPendaftar();
                        return;
                    }
                   
                    // list untuk menyimpan urutan index setiap mahasiswa
                    ArrayList<Mahasiswa> urutan_mahasiswa = new ArrayList<>();
        
                    int nomhs = 1;
                    for (Mahasiswa mhs : DaftarMahasiswa)
                    {
                        // menampilkan informasi data maahasiswa 
                        System.out.println((nomhs)+"."+mhs.getNim()+"|"+mhs.getNama()+"|"+mhs.getProdi()+"|"+mhs.getSmstr());
                        urutan_mahasiswa.add(mhs);
                        nomhs++;
                    }

                    // input pemilihan mahasiswa untuk pendaftaran lomba
                    System.out.print("Masukkan index calon peserta : ");
                    int index = input.nextInt();
                    input.nextLine();

                    if (index >=1 && index <= urutan_mahasiswa.size())
                    {

                        Mahasiswa mhs_terpilih = urutan_mahasiswa.get(index-1);

                        // cek kondisi supaya tidak terjadi duplikat pendaftaran pada nama lomba yang sama
                        if(Daftar.cekDaftar(DaftarRegistrasi, lomba_terpilih,mhs_terpilih))
                        {
                            System.out.println("Pendaftaran Gagal - Anda sudah mendaftar lomba yang sama ! ");
                            System.out.println("NIM : "+mhs_terpilih.getNim());
                            System.out.println("Nama : "+mhs_terpilih.getNama());
                            System.out.println("Ikut lomba : "+lomba_terpilih.getNamaLomba());
                            return;
                        }
                        else
                        {
                            System.out.println("MEMILIH MAHASISWA BERNAMA "+mhs_terpilih.getNama().toUpperCase());
                            mhs_terpilih.getDataMahasiwa();
                            System.out.print("Tanggal Daftar (DD-MM-YYY) : ");
                            String tgl = input.nextLine();

                            // data akan tersimpan di objek sementara
                            Daftar sementara = new Daftar(mhs_terpilih, lomba_terpilih,mhs_terpilih.getNim(), tgl);
                            DataSementara.add(sementara);
                            sementara.updateStatus("diproses");
                           
                            /*Daftar daftar = new Daftar(mhs_terpilih,lomba_terpilih,mhs_terpilih.getNim(),tgl);
                            daftar.prosesPendaftaran(lomba_terpilih); 
                            DaftarRegistrasi.add(daftar);*/
                            if(lomba_terpilih instanceof LombaAkademik)
                                {
                                    System.out.println("---> INFORMASI LOMBA AKADEMIK <---");
                                    ((LombaAkademik) lomba_terpilih).tampilMatUjian();
                                }          
                        }
                    }
                    else {System.out.println("Nomor urut tidak valid ! ");}
                }
                    
            else 
                {
                    System.out.println("Pendaftaran dibatalkan");
                    return;
                }
        }
        else
        {
            System.out.println("Pilihan tidak valid !");
        }
                           
    }

    public static void LihatBuktiDaftar()
    {
        int opsicek;
        do
        {
            System.out.println("\n--->MENU BUKTI PENDAFTARAN LOMBA <---");
            System.out.println("1. Cek calon peserta lomba");
            System.out.println("2. Cek peserta lomba resmi terdaftar ");
            System.out.println("3. Keluar dari menu bukti pendaftaran");
            System.out.print("Pilih opsi : ");
            opsicek = input.nextInt();
            input.nextLine();

            switch (opsicek)
            {
                case 1 : CekCalonPeserta(); break;
                case 2 : CekPesertaResmi(); break;
                case 3 : break;
                default: System.out.println("Pilihan tidak valid ! ");
            }
        }while(opsicek != 3);
    }

    public static void CekCalonPeserta()
    {
        if(DataSementara.isEmpty())
        {
            System.out.println("Data calon peserta belum ada ! ");
            return;
        }
        else
        {
            ArrayList<Daftar> calonPeserta = new ArrayList<>();

            System.out.println("\n----> DATA CALON PESERTA LOMBA <----");
            int nodftr = 1;
            for (Daftar d : DataSementara)
            {
                System.out.println((nodftr)+"."+d.getiddaftar()+"|"+d.getclassMahasiswa().getNim()+"|"+d.getclassMahasiswa().getNama()+"|"+d.getclassLomba().getId()+"|"+d.getclassLomba().getNamaLomba()+"|"+d.getstatus());
                calonPeserta.add(d);
                nodftr++;
            }
            System.out.print("Masukan index calon pendaftar : ");
            int index_bukti_dftr = input.nextInt();
            input.nextLine();
            if (index_bukti_dftr >=1 && index_bukti_dftr <= calonPeserta.size())
            {
                Daftar pendaftar_terpilih = calonPeserta.get(index_bukti_dftr-1);
                pendaftar_terpilih.tampilBuktiDaftar();
            }
            else
            {
                System.out.println("Pemilihan bukti pendaftaran sementara tidak valid !");
                return;
            }
        }
    }

    public static void CekPesertaResmi()
    {
        if (DaftarRegistrasi.isEmpty())
        {
            System.out.println("Bukti pendaftaran peserta lomba belum ada !");
            return;
        }
        else
        {
            ArrayList<Daftar> peserta_resmi = new ArrayList<>();
            System.out.println("\n----> DATA PESERTA LOMBA <----");
            int nodftrF = 1;
            for (Daftar d : DaftarRegistrasi)
            {
                System.out.println((nodftrF)+"."+d.getiddaftar()+"|"+d.getclassMahasiswa().getNim()+"|"+d.getclassMahasiswa().getNama()+"|"+d.getclassLomba().getId()+"|"+d.getclassLomba().getNamaLomba()+"|"+d.getstatus());
                peserta_resmi.add(d);
                nodftrF++;
            }
    
            System.out.print("Masukan index  pendaftar : ");
            int index_bukti_dftr = input.nextInt();
            input.nextLine();
            if (index_bukti_dftr >=1 && index_bukti_dftr <= peserta_resmi.size())
            {
                Daftar pendaftar_terpilih = peserta_resmi.get(index_bukti_dftr-1);
                pendaftar_terpilih.tampilBuktiDaftar();
            }
            else
            {
                System.out.println("Pemilihan bukti pendaftaran tidak valid !");
                return;
            }
        }

    }
        
    public static void UpdateStatus()
    {
        System.out.println("\n---> UPDATE STATUS PENDAFTARAN <---");
        if(DataSementara.isEmpty())
        {
            System.out.println("Belum ada pendaftaran");
            return;
        }
        else
        {
            System.out.println("----> DATA PENDAFTAR YANG SEDANG MENUNGGU PERSETUJUAN <----");
            ArrayList<Daftar> urutanDaftar = new ArrayList<>();
            int nodftr = 1;
            for (Daftar d : DataSementara)
            {
                System.out.println((nodftr)+"."+d.getiddaftar()+"|"+d.getclassMahasiswa().getNama()+"|"+d.getclassLomba().getNamaLomba());
                urutanDaftar.add(d);
                nodftr++;
            }
            System.out.print("Masukan index pendaftar : ");
            int index_pendaftar = input.nextInt();
            input.nextLine();

            if (index_pendaftar >=1 && index_pendaftar <= urutanDaftar.size())
            {
                Daftar pendaftar_terpilih = urutanDaftar.get(index_pendaftar-1);

                System.out.println("Anda memilih peserta pendaftaran atas nama "+pendaftar_terpilih.getclassMahasiswa().getNama());
                pendaftar_terpilih.tampilBuktiDaftar();

                System.out.print("Status baru (Disetujui/Ditolak) : ");
                String statusBaru = input.nextLine();
                pendaftar_terpilih.updateStatus(statusBaru);
                if (statusBaru.equalsIgnoreCase("ditolak"))
                {
                    System.out.print("Apakah anda yakin ingin menolak pendaftar bernama "+pendaftar_terpilih.getclassMahasiswa().getNama()+" ? (Y/N) : ");
                    String tolak = input.nextLine();
                    if (tolak.equalsIgnoreCase("y")) 
                        {
                            Lomba lmb = pendaftar_terpilih.getclassLomba();
                            lmb.kurangiDaftar();
                            DataSementara.remove(pendaftar_terpilih);
                            System.out.println("ID pendaftar "+pendaftar_terpilih.getiddaftar()+" atas nama "+pendaftar_terpilih.getclassMahasiswa().getNama()+" telah di "+statusBaru+" !");
                        }
                    else
                    {
                        System.out.println("Penolakan dibatalkan");
                        return;
                    }
                }
                else if (statusBaru.equalsIgnoreCase("disetujui"))
                {
                    Lomba lmb = pendaftar_terpilih.getclassLomba();

                    lmb.tmbhPendaftar();
                    DataSementara.remove(pendaftar_terpilih);
                    DaftarRegistrasi.add(pendaftar_terpilih);
                    System.out.println("ID pendaftar "+pendaftar_terpilih.getiddaftar()+" atas nama "+pendaftar_terpilih.getclassMahasiswa().getNama()+" telah di "+statusBaru+" !");
                }
                else 
                {
                    System.out.println("Update Status tidak valid ! ");
                    return;
                }
            }
            else
            {
                System.out.println("Index pendaftar tidak valid !");
                return;
            }
            
        }
    }

    public static void LihatPesertaLomba()
    {
        if(DaftarRegistrasi.isEmpty())
        {
            System.out.println("Peserta Lomba belum ada ");
            return;
        }
        else
        {
            System.out.println("\n---> DATA PESERTA LOMBA <---");
            ArrayList<Daftar> cekdaftar = new ArrayList<>(); 
            int nopst = 1;
            for(Daftar cekd : DaftarRegistrasi)
            {
                System.out.println((nopst)+" .ID Peserta : "+cekd.getiddaftar()+
                " | Nama Peserta : "+cekd.getclassMahasiswa().getNama()+"| Asal Kampus : "+cekd.getclassMahasiswa().getKampusAsal()+
                " | Prodi : "+cekd.getclassMahasiswa().getProdi()+" | ID Lomba : "+cekd.getclassLomba().getId()+
                " | Lomba Diikuti : "+cekd.getclassLomba().getNamaLomba()+" | Bidang Lomba : "+cekd.getclassLomba().getBidang());
                cekdaftar.add(cekd);
                nopst++;
            }

        }

    }
}