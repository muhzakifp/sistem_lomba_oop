import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    // ARRAY LIST SETIAP CLASS
    static ArrayList<Mahasiswa> DaftarMahasiswa = new ArrayList<>();
    static ArrayList<Lomba> DaftarLomba = new ArrayList<>();
    static ArrayList<Daftar> DaftarRegistrasi = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        // DATA PERLOMBAAN
        DaftarLomba.add(new Lomba("LO2624001", "Lomba pengembangan Website", "Teknologi", "1-Januari-2026", 20));
        DaftarLomba.add(new Lomba("LO2624002", "Lomba robotik", "Teknologi", "12-Januari-2026", 20));
        DaftarLomba.add(new Lomba("LO2624003", "catur", "Olahraga", "11-Januari-2026", 10));
        DaftarLomba.add(new Lomba("LO2624004", "Futsal", "Olahraga", "15-Februari-2026", 15));
        DaftarLomba.add(new Lomba("LO2624005", "Desain Poster", "Seni dan Kreativitas", "2-Februari-2026", 5));
        DaftarLomba.add(new Lomba("LO2624006", "Hafidz Qur'an", "Keagamaan", "5-Februari-2026", 5));
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
            System.out.println("5. Keluar");
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
                case 5: System.out.println("Anda telah keluar"); break;
                default : System.out.println("Pilihan tidak valid !"); 
            }
        }while (opsi !=5);
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
        if (smstr<=0 || smstr>=5 )
        {
            System.out.println("--- Semester tidak valid ! ---");
            System.out.println("--- Pendaftar wajib Mahasiswa Semester 1-4 ---");
            return;
        }
        System.out.print("Kampus Asal : ");
        kampus = input.nextLine().trim();
        System.out.print("Nomor HP aktif : ");
        nohp = input.nextLine();

        Mahasiswa mhs = new Mahasiswa(nim, nama, prodi, smstr, kampus, nohp); // objek mhs dibuat 
        DaftarMahasiswa.add(mhs); // data objek akan tersimpan di ArrayList DaftarMahasiswa
        System.out.println("Mahasisswa berhasil ditambahkan ! ");
    }
    
    // FUNCTION DAFTAR LOMBA
    public static void DaftarLomba()
    {
        // ARRAYLIST UNTUK MENAMPILKAN URUTAN LOMBA
        ArrayList<Lomba> urutan_lomba = new ArrayList<>();

        int no = 1;
        if(DaftarMahasiswa.isEmpty()) // pengcekan kondisi jika ArrayList DaftarMahasiswa kosong 
        {
            System.out.println("Belum ada data Mahasiswa ! ");
            return;
        }
        System.out.println("\n---> DAFTAR LOMBA TERSEDIA <---"); 
        System.out.println("[--LOMBA UMUM--]");
        for (Lomba l : DaftarLomba) 
        {
            if(!(l instanceof LombaAkademik))
            {
                System.out.println((no)+"."+l.getId()+"|"+l.getNamaLomba()+"|"+l.getBidang());
                urutan_lomba.add(l);
                no++;
            }
        }
        System.out.println("[--LOMBA AKADEMIK--]");
        for(Lomba l : DaftarLomba)
        {
            if(l instanceof LombaAkademik)
            {
                System.out.println((no)+"."+l.getId()+"|"+l.getNamaLomba()+"|"+l.getBidang());
                urutan_lomba.add(l);
                no++;
            }
        }

        System.out.print("Pilih index lomba : ");
        int pil_lmb =  input.nextInt();
        input.nextLine();
        
        if (pil_lmb >=1 && pil_lmb <= urutan_lomba.size())
        {
            Lomba lomba_terpilih = urutan_lomba.get(pil_lmb-1);

            System.out.println("\n--- ANDA MEMILIH LOMBA "+lomba_terpilih.getNamaLomba().toUpperCase() +"---");
            lomba_terpilih.tampilInformasi();
            
            System.out.print("Apakah yakin ingin mendaftar? (Y/N) : ");
            String konf = input.nextLine();
            if (konf.equalsIgnoreCase("y"))
                {
                    ArrayList<Mahasiswa> urutan_mahasiswa = new ArrayList<>();

                    int nomhs = 1;
                    for (Mahasiswa mhs : DaftarMahasiswa)
                    {
                        System.out.println((nomhs)+"."+mhs.getNim()+"|"+mhs.getNama()+"|"+mhs.getProdi()+"|"+mhs.getSmstr());
                        urutan_mahasiswa.add(mhs);
                        nomhs++;
                    }
                    System.out.print("Masukkan index peserta : ");
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

                        System.out.println("MEMILIH MAHASISWA BERNAMA "+mhs_terpilih.getNama().toUpperCase());
                        mhs_terpilih.getDataMahasiwa();
                        System.out.print("Tanggal Daftar (DD-MM-YYY) : ");
                        String tgl = input.nextLine();
                        
                        Daftar daftar = new Daftar(mhs_terpilih,lomba_terpilih,mhs_terpilih.getNim(),tgl);
                        daftar.prosesPendaftaran(lomba_terpilih); 
                        DaftarRegistrasi.add(daftar);
                        
                        if(lomba_terpilih instanceof LombaAkademik)
                            {
                                System.out.println("---> INFORMASI LOMBA AKADEMIK <---");
                                ((LombaAkademik) lomba_terpilih).tampilMatUjian();
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
        System.out.println("\n---> BUKTI PENDAFTARAN LOMBA <---");
        if(DaftarRegistrasi.isEmpty())
        {
            System.out.println("Belum ada Pendaftaran");
            return;
        }
        else
        {
            ArrayList<Daftar> buktiDaftar = new ArrayList<>();
            int nodftr = 1;
            for (Daftar d : DaftarRegistrasi)
            {
                System.out.println((nodftr)+"."+d.getiddaftar()+"|"+d.getclassMahasiswa().getNim()+"|"+d.getclassMahasiswa().getNama()+"|"+d.getclassLomba().getId()+"|"+d.getclassLomba().getNamaLomba()+"|"+d.getstatus());
                buktiDaftar.add(d);
                nodftr++;
            }
            System.out.print("Masukan index pendaftar : ");
            int index_bukti_dftr = input.nextInt();
            input.nextLine();

            if (index_bukti_dftr >=1 && index_bukti_dftr <= buktiDaftar.size())
            {
                Daftar pendaftar_terpilih = buktiDaftar.get(index_bukti_dftr-1);
                pendaftar_terpilih.tampilBuktiDaftar();
            }
            else
            {
                System.out.println("Pemilihan bukti pendaftaran tidak valid !");
            }
        }
    }

    public static void UpdateStatus()
    {
        System.out.println("\n---> UPDATE STATUS PENDAFTARAN <---");
        if(DaftarRegistrasi.isEmpty())
        {
            System.out.println("Belum ada pendaftaran");
            return;
        }
        else
        {
            ArrayList<Daftar> urutanDaftar = new ArrayList<>();
            int nodftr = 1;
            for (Daftar d : DaftarRegistrasi)
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
                            DaftarRegistrasi.remove(pendaftar_terpilih);
                            System.out.println("ID pendaftar "+pendaftar_terpilih.getiddaftar()+" atas nama "+pendaftar_terpilih.getclassMahasiswa().getNama()+" telah di "+statusBaru+" !");
                        }
                    else
                    {
                        System.out.println("Penolakan dibatalkan");
                        return;
                    }
                }
                else
                {
                    System.out.println("ID pendaftar "+pendaftar_terpilih.getiddaftar()+" atas nama "+pendaftar_terpilih.getclassMahasiswa().getNama()+" telah di "+statusBaru+" !");
                }
            }
            else
            {
                System.out.println("Index pendaftar tidak valid !");
                return;
            }
            
        }
    }
}