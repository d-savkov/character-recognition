package com.example.bsuir.config;

import com.example.bsuir.image.repository.ImageRepository;
import com.example.bsuir.image.service.ImageService;
import com.example.bsuir.person.model.Person;
import com.example.bsuir.person.repository.PersonRepository;
import com.example.bsuir.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationEventConfig {

    private static final AtomicInteger SUCCESSFUL = new AtomicInteger();
    private static final AtomicInteger FAILED = new AtomicInteger();
    private static final AtomicInteger TOTAL = new AtomicInteger();
    private final ImageService imageService;
    private final PersonService personService;
    private final ImageRepository imageRepository;
    private final PersonRepository personRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void afterApplicationReady() {
        imageRepository.deleteAll();
        personRepository.deleteAll();
        saveSampleData();
        personService.getAll().forEach(person -> {
            try {
                log.info("Processing images of {}:", person.getName());
                processPersonImages(person);
            } catch (FileNotFoundException e) {
                log.warn("Can't find images of {}", person.getName());
            }
        });
        log.info("Summary: {}/{} images was successfully processed, {} failed",
                 SUCCESSFUL.get(),
                 TOTAL.get(),
                 FAILED.get()
        );
    }

    private void processPersonImages(Person person) throws FileNotFoundException {
        File imageFolder = ResourceUtils.getFile("classpath:image/" + person.getImagesSource());
        for (File file : Objects.requireNonNull(imageFolder.listFiles())) {
            String filePath = String.format("%s/%s", person.getImagesSource(), file.getName());
            FileItem fileItem = new DiskFileItem(
                    "file",
                    "image/jpeg",
                    true,
                    file.getPath(),
                    Integer.MAX_VALUE,
                    file.getParentFile()
            );
            try {
                IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            try {
                imageService.saveImage(multipartFile, filePath, person);
                SUCCESSFUL.incrementAndGet();
                log.info(" - {} successfully processed!", filePath);
            } catch (IllegalArgumentException ex) {
                FAILED.incrementAndGet();
                log.error(" - {} can't be processed!", filePath);
            }
            TOTAL.incrementAndGet();
        }
    }

	// TODO: Remove on production
	private void saveSampleData() {
		personRepository.save(new Person("Eddard Stark", "Lord of Winterfell and Warden of the North. The childhood friend of Robert Baratheon,  he was a key player in Robert's rebellion,  helping him win the throne from the Targaryens. He is married to Catelyn Tully.", "Sean Bean", "eddard-stark"));
		personRepository.save(new Person("Margaery Tyrell", "Mace Tyrell's only daughter,  Margaery is as clever as she is beautiful.", "Natalie Dormer", "margaery-tyrell"));
		personRepository.save(new Person("Robert Baratheon", "King of the Seven Kingdoms of Westeros and longtime friend of Ned Stark. Robert was engaged to Ned's sister,  Lyanna,  whose abduction by Prince Rhaegar Targaryen incited Robert's uprising against the throne. When Lyanna died during the war,  Robert married Cersei Lannister.", "Mark Addy", "robert-baratheon"));
		personRepository.save(new Person("Tyrion Lannister", "Called \"The Imp\", because of his small stature,  he is the youngest son of Tywin Lannister,  the richest lord in the Seven Kingdoms and younger brother to Queen Cersei and Ser Jaime Lannister. What he lacks in height,  he makes up in wit.", "Peter Dinklage", "tyrion-lannister"));
		personRepository.save(new Person("Cersei Lannister", "Queen of the Seven Kingdoms and wife of King Robert. Fiercely protective of her three children,  she also has an unusually tight connection to her twin brother,  Jaime.", "Lena Headey", "cersei-lannister"));
		personRepository.save(new Person("Catelyn Stark", "Wife of Ned Stark,  born Catelyn of House Tully,  the ruling house of the Riverlands. Originally engaged to Ned’s older brother Brandon,  she married Ned when he was killed.", "Michelle Fairley", "catelyn-stark"));
		personRepository.save(new Person("Jaime Lannister", "The Queen's twin brother. It was he who murdered the previous King (Aerys),  despite have sworn to protect him. This deed earned him his nickname: “Kingslayer.”", "Nikolaj Coster-Waldau", "jaime-lannister"));
		personRepository.save(new Person("Daenerys Targaryen", "Princess of House Targaryen,  living in exile in Essos with her brother,  Viserys.", "Emilia Clarke", "daenerys-targaryen"));
		personRepository.save(new Person("Viserys Targaryen", "Prince of House Targaryen,  whose father,  the \"Mad\" King Aerys was usurped by King Robert.  Viserys lives in self-exile with his sister Daenerys and is obsessed with regaining his family's throne.", "Harry Lloyd", "viserys-targaryen"));
		personRepository.save(new Person("Jon Snow", "Ned's bastard son. He joins the Night’s Watch,  following in the footsteps of his Uncle Benjen. His direwolf is named Ghost.", "Kit Harington", "jon-snow"));
		personRepository.save(new Person("Robb Stark", "The oldest of Ned and Cat's five children. His direwolf is named Grey Wind.", "Richard Madden", "robb-stark"));
		personRepository.save(new Person("Sansa Stark", "Ned and Cat's oldest daughter. Demure and ladylike,  her dream is to marry Prince Joffrey. Her direwolf is named Lady.", "Sophie Turner", "sansa-stark"));
		personRepository.save(new Person("Arya Stark", "The younger of Ned and Cat's two daughters. A tomboy,  she would rather fence than dance. Her direwolf is named Nymeria,  after a warrior queen.", "Maisie William", "arya-stark"));
		personRepository.save(new Person("Bran Stark", "Ned and Cat's younger son,  who has an inquisitive nature and a penchant for climbing Winterfell's walls. His direwolf is named Summer.", "Isaac Hempstead-Wright", "bran-stark"));
		personRepository.save(new Person("Rickon Stark", "Rickon is the youngest of the Stark children. His direwolf is named Shaggydog.", "Art Parkinson", "rickon-stark"));
		personRepository.save(new Person("Joffrey Baratheon", "The oldest of King Robert and Queen Cersei's children.", "Jack Gleeson", "joffrey-baratheon"));
		personRepository.save(new Person("Jorah Mormont", "A knight now in exile in Essos who advises Viserys and Daenerys Targaryen as they make a life among the Dothraki.", "Iain Glen", "jorah-mormont"));
		personRepository.save(new Person("Theon Greyjoy", "Ned's ward,  heir to House Greyjoy. He was brought to Winterfell after his father,  Balon Greyjoy,  tried to rise against King Robert. Although raised among the Stark children,  he is keenly aware he is not one of them.", "Alfie Allen", "theon-greyjoy"));
		personRepository.save(new Person("Petyr Baelish", "Master of Coin and member of the King’s Small Council,  he grew up with Catelyn Stark for whom he still harbors feelings. Beyond his official duties,  he is the eyes and ears of King's Landing along with Varys.", "Aidan Gillen", "petyr-baelish"));
		personRepository.save(new Person("Sandor Clegane", "Personal bodyguard to Prince Joffrey,  half his face is covered in burn scars as a result of his brother thrusting him into a fire.", "Rory Mccann", "sandor-clegane"));
		personRepository.save(new Person("Samwell Tarly", "An overweight and timid,  Night’s Watch recruit who is forced to join after being disinherited by his family. He falls under Jon Snow's protection and becomes his best friend.", "John Bradley", "samwell-tarly"));
		personRepository.save(new Person("Renly Baratheon", "King Robert’s youngest brother,  Master of Laws on the King’s Small Council and Lord of Storm’s End. He is a close friend of Loras Tyrell,  the Knight of Flowers.", "Gethin Anthony", "renly-baratheon"));
		personRepository.save(new Person("Ros", "A prostitute from the North who journeyed to King's Landing.", "Esme Bianco", "ros"));
		personRepository.save(new Person("Jeor Mormont", "Father of the exiled Jorah Mormont and former lord of Bear Island. He is now the lord commander of the Night’s Watch and a friend to Jon Snow.", "James Cosmo", "jeor-mormont"));
		personRepository.save(new Person("Gendry", "An apprentice smith in King’s Landing who is of interest to Jon Arryn and Ned Stark.", "Joe Dempsie", "gendry"));
		personRepository.save(new Person("Lysa Arryn", "Catelyn Stark's younger sister and the wife of Jon Arryn,  the Hand of the King. Both she and Catelyn were raised with Petyr Baelish.", "Kate Dickie", "lysa-arryn"));
		personRepository.save(new Person("Robin Arryn", "Lysa and Jon Arryn's son. He has not yet been weaned from his mother.", "Lino Facioli", "robin-arryn"));
		personRepository.save(new Person("Bronn", "A mercenary who joins Tyrion Lannister and becomes his right-hand man.", "Jerome Flynn", "bronn"));
		personRepository.save(new Person("Grand Maester Pycelle", "The aged maester of King’s Landing and a member of the Small Council.", "Julian Glover", "grand-maester-pycelle"));
		personRepository.save(new Person("Varys", "A eunuch and a member of the Small Council. A master of disguise,  he along with Littlefinger are always aware of what goes on in Court.", "Conleth Hill", "varys"));
		personRepository.save(new Person("Loras Tyrell", "A handsome and popular young knight,  referred to as the Knight of Flowers,  Loras is a close friend of Renly Barthenon.", "Finn Jones", "loras-tyrell"));
		personRepository.save(new Person("Shae", "A prostitute-turned-mistress of Tyrion Lannister.", "Sibel Kekilli", "shae"));
		personRepository.save(new Person("Benjen Stark", "Ned's younger brother and First Ranger of the Night Watch,  he is a hero of Jon Snow's.", "Joseph Mawle", "benjen-stark"));
		personRepository.save(new Person("Barristan Selmy", "As a member of the Kingsguard under the former king,  Aerys,  he never wavered in his oath to protect. For his commitment,  Robert pardoned him and promoted him to Lord Commander.", "Ian Mcelhinney", "barristan-selmy"));
		personRepository.save(new Person("Khal Drogo", "The warrior leader of a vast tribe of horseman in Essos.", "Jason Momoa", "khal-drogo"));
		personRepository.save(new Person("Hodor", "One of Winterfell many loyal servants. Simple-minded and oversized,  Hodor will serve as Bran's primary method of transport.", "Kristian Nairn", "hodor"));
		personRepository.save(new Person("Lancel Lannister", "Robert's squire. A first cousin of Queen Cersei,  he bears some resemblance to her brother Jaime.", "Eugene Simon", "lancel-lannister"));
		personRepository.save(new Person("Maester Luwin", "Maester of Winterfell,  he provides wise counsel to Ned and serves as tutor to the children.", "Donald Sumpter", "maester-luwin"));
		personRepository.save(new Person("Alliser Thorne", "Master-at-arms of Castle Black,  it is his job to turn the undisciplined and obstinate recruits into rangers of the Night Watch. He resents Jon Snow.", "Owen Teale", "alliser-thorne"));
		personRepository.save(new Person("Osha", "A wildling woman who helps raise Bran.", "Natalia Tena", "osha"));
		personRepository.save(new Person("Maester Aemon", "The elderly,  blind maester of the Night's Watch who is sympathetic to Jon Snow's plight.", "Peter Vaughan", "maester-aemon"));
		personRepository.save(new Person("Talisa Stark", "A native of Volantis,  Talisa is a healer.", "Oona Chaplin", "talisa-stark"));
		personRepository.save(new Person("Brienne of Tarth", "Brienne is a highborn lady who would rather be a knight. As Catelyn Stark's envoy,  she is tasked with escorting Jaime Lannister back to King's Landing.", "Gwendoline Christie", "brienne-of-tarth"));
		personRepository.save(new Person("Davos Seaworth", "A reformed smuggler,  it was Davos who provided Stannis' forces much-needed provisions during the long siege of Storm's End.", "Liam Cunningham", "davos-seaworth"));
		personRepository.save(new Person("Tywin Lannister", "Father of Cersei,  Jaime and Tyrion,  he is the most powerful lord in the Seven Kingdoms,  because of his enormous wealth. His wife died giving birth to Tyrion,  hence his hatred for him.", "Charles Dance", "tywin-lannister"));
		personRepository.save(new Person("Stannis Baratheon", "A serious man who preferred the solitude of the family seat in Dragonstone to King's Landing,  Stannis is still challenging his nephew Joffrey for the Iron Throne.", "Stephen Dillane", "stannis-baratheon"));
		personRepository.save(new Person("Ygritte", "A wildling woman that Jon Snow encountered traveling beyond the Wall,  Ygritte is fiercely proud of her free status. Her opinions have clouded Jon’s stance about the Night’s Watch.", "Rose Leslie", "ygritte"));
		personRepository.save(new Person("Balon Greyjoy", "Head of the ruling house of the Iron Islands,  Balon Greyjoy lost two of his sons in a failed revolt against Robert Baratheon. His only surviving son,  Theon,  was raised as a ward by the Starks.", "Patrick Malahide", "balon-greyjoy"));
		personRepository.save(new Person("Roose Bolton", "Lord of the Dreadfort and head of House Bolton,  Roose takes pride in his family's sigil of a flayed man.", "Michael Mcelhatton", "roose-bolton"));
		personRepository.save(new Person("Gilly", "A wildling from Craster’s Keep,  Gilly and her son Sam journeyed south of the Wall under the protection of Sam Tarly.", "Hannah Murray", "gilly"));
		personRepository.save(new Person("Podrick Payne", "Quiet and unassuming,  Podrick is Tyrion’s squire.", "Daniel Portman", "podrick-payne"));
		personRepository.save(new Person("Melisandre", "A Red Priestess from Asshai,  Melisandre worships the Lord of Light. She believes her visions will lead her to the true king of Westeros.", "Carice Van Houten", "melisandre"));
		personRepository.save(new Person("Yara Greyjoy", "Theon's older sister,  she is the only child of Balon Greyjoy remaining on the Iron Islands.", "Gemma Whelan", "yara-greyjoy"));
		personRepository.save(new Person("Jaqen H’ghar", "Jaqen helped Arya escape from Harrenhal and provided her with an iron coin to ensure her safe passage to Braavos.", "Tom Wlaschiha", "jaqen-h’ghar"));
		personRepository.save(new Person("Grey Worm", "One of the highly-trained Unsullied fighters from Astapor,  he was named by his fellow soldiers as their leader when they were set free by Daenerys Targaryen.", "Jacob Anderson", "grey-worm"));
		personRepository.save(new Person("Beric Dondarrion", "Lord Beric of Blackhaven,  he was tasked by Ned Stark,  then Hand of the King,  to bring Gregor Clegane to justice. A follower of the Red faith,  Beric has been brought back to life several times by Thoros of Myr.", "Richard Dormer", "beric-dondarrion"));
		personRepository.save(new Person("Missandei", "Formerly a slave in Astapor,  Missandei is fluent in the Common Tongue and High Valyrian.", "Nathalie Emmanuel", "missandei"));
		personRepository.save(new Person("Mance Rayder", "Once a brother of the Night’s Watch,  Mance abandoned his vows to join the wildlings. He serves as their King Beyond the Wall.", "Ciaran Hinds", "mance-rayder"));
		personRepository.save(new Person("Tormund", "A legendary wildling leader who supports Mance Rayder,  Tormund is a fair and formidable warrior.", "Kristofer Hivju", "tormund"));
		personRepository.save(new Person("Ramsay Snow", "A bastard son of Roose Bolton,  Ramsay's bloodlust is even stronger than his father's.", "Iwan Rheon", "ramsay-snow"));
		personRepository.save(new Person("Olenna Tyrell", "Matriarch of House Tyrell,  Lady Olenna is not shy about sharing her opinions. Although fond of her granddaughter Margaery,  she feels less generous toward the men in her family.", "Diana Rigg", "olenna-tyrell"));
		personRepository.save(new Person("Thoros of Myr", "A native of the Free City of Myr and a famous soldier,  he has since joined the outlaw group the Brotherhood Without Banners. Although Thoros serves as a priest for the Lord of Light,  he is fond of drink.", "Paul Kaye", "thoros-of-myr"));
		personRepository.save(new Person("Orell", "A wildling scout,  he travels with an eagle.", "Mackenzie Crook", "orell"));
		personRepository.save(new Person("Qyburn", "Although trained in the medical arts,  Qyburn was censured by the Citadel for his unauthorized experiments and does not wear the chains of a maester.", "Anton Lesser", "qyburn"));
		personRepository.save(new Person("Brynden Tully", "Brynden is known as “the Blackfish, ” due to the strained relationship between him and his older brother Hoster. Alongside Barristan Selmy,  Brynden showed great heroism during the War of the Ninepenny Kings,  a rebellion in the East that threatened the Seven Kingdoms.", "Clive Russell", "brynden-tully"));
		personRepository.save(new Person("Tommen Baratheon", "The youngest of Cersei’s children,  Tommen inherited the throne after the death of his brother Joffrey.", "Dean-Charles Chapman", "tommen-baratheon"));
		personRepository.save(new Person("Daario Naharis", "Daenerys' advisor and lover.", "Michiel Huisman", "daario-naharis"));
		personRepository.save(new Person("Oberyn Martell", "A prince of Dorne,  Oberyn Martell is also a fierce fighter who earned the nickname,  the \"Red Viper\".", "Pedro Pascal", "oberyn-martell"));
		personRepository.save(new Person("Ellaria Sand", "Prince Oberyn Martell’s paramour.", "Indira Varma", "ellaria-sand"));
		personRepository.save(new Person("Myrcella Baratheon", "Fair-haired and demure,  Myrcella now lives in Dorne as part of a deal her uncle Tyrion made with House Martell to align their houses and keep the princess safe.", "Nell Tiger Free", "myrcella-baratheon"));
		personRepository.save(new Person("Obara Sand", "The eldest of Oberyn Martell's bastard daughters,  Obara is as dangerous with a spear as he was.", "Keisha Castle-Hughes", "obara-sand"));
		personRepository.save(new Person("Nym Sand", "Half-Dornish and elegant,  she uses the whip as her weapon of choice.", "Jessica Henwick", "nym-sand"));
		personRepository.save(new Person("Tyene Sand", "The youngest of the Sand Snakes,  Tyene is proficient with daggers.", "Rosabell Laurenti Sellers", "tyene-sand"));
		personRepository.save(new Person("High Sparrow", "The High Sparrow leads a group of religious zealots determined to combat vice in King's Landing.", "Jonathan Pryce", "high-sparrow"));
		personRepository.save(new Person("Trystane Martell", "A prince of Dorne and heir to Doran Martell,  Trystane is engaged to Myrcella Baratheon.", "Toby Sebastian", "trystane-martell"));
		personRepository.save(new Person("Doran Martell", "A firm and diplomatic leader,  Doran stayed largely neutral in the War of Five Kings.", "Alexander Siddig", "doran-martell"));
		personRepository.save(new Person("Euron Greyjoy", "The younger brother of Balon Greyjoy,  he has recently returned to Pyke to take the Salt Throne.", "Pilou Asbæk", "euron-greyjoy"));
		personRepository.save(new Person("Lady Crane", "An actor with a Braavosi traveling company,  she plays Cersei Lannister in their production of “The Bloody Hand.”", "Essie Davis", "lady-crane"));
		personRepository.save(new Person("High Priestess", "The leader of the Dosh Khaleen,  and herself the widow of a great khal,  she was among those present when Daenerys ate a stallion’s heart.", "Souad Faress", "high-priestess"));
		personRepository.save(new Person("Randyll Tarly", "A legendary soldier,  Randyll Tarly has no patience for his son Samwell’s studious nature. He forced Sam to join the Night’s Watch,  making his second son Dickon heir to Horn Hill.", "James Faulkner", "randyll-tarly"));
		personRepository.save(new Person("Izembaro", "The playwright of a traveling company who wrote and directs “The Bloody Hand, ” a theatrical production of The War of Five Kings currently running in Braavos. Izembaro also stars as Robert Baratheon and Tywin Lannister.", "Richard E. Grant", "izembaro"));
		personRepository.save(new Person("Brother Ray", "A former soldier haunted by the atrocities he committed on the battlefield,  Brother Ray is a septon of boundless compassion and kindness.", "Ian Mcshane", "brother-ray"));
		personRepository.save(new Person("Archmaester Ebrose", "A Citadel archmaester,  Ebrose is highly knowledgeable in medicine and the human body. He oversees several of Samwell Tarly’s duties,  and is stern but affectionate to the new trainee.", "Jim Broadbent", "archmaester-ebrose"));
	}
}
