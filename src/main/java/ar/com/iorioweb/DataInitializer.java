package ar.com.iorioweb;
import ar.com.iorioweb.model.*;
import ar.com.iorioweb.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component //esto indica que springboot debe manejar esta clase

public class DataInitializer implements CommandLineRunner{
	//1. A continuación inyectaremos todos los repositorios
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Autowired
    private BiografiaRepository biografiaRepository;

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private VideoRepository videoRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Ejecutar la inicialización SOLO si la base de datos está vacía.
        if (artistaRepository.count() == 0) {
            System.out.println("-> Iniciando la carga de datos del artista Iorio...");
            
            // ==========================================================
            // A. CREAR AL ARTISTA PRINCIPAL (SOLO UNO)
            // ==========================================================
            Artista iorio = Artista.builder()
                .nombreReal("Ricardo Horacio Iorio")
                .fotoPerfilPrincipal("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309127/iorio_biaga9.jpg")
                .build();
            
            // ¡IMPORTANTE! Guardar el artista para que Hibernate le asigne un ID
            iorio = artistaRepository.save(iorio);

            // ==========================================================
            // B. CREACION DE  BIOGRAFÍA (UNO A UNO) - USANDO BUILDER
            // ==========================================================
            String biografiaCompletaTexto = "La Voz Fundacional del Heavy Metal Argentino\r\n"
            		+ "Ricardo Iorio (1962 – 2023) fue la figura más influyente y controversial del heavy metal en Argentina. Compositor, bajista y cantante, su obra no solo sentó las bases del género en el país, sino que también se consolidó como un referente lírico al explorar la identidad nacional, las problemáticas sociales, la marginalidad y el ser del gaucho moderno. Su carrera, que abarcó más de cuatro décadas, se divide en cuatro capítulos fundamentales.\r\n"
            		+ "\r\n"
            		+ "V8 (1979-1987): El Nacimiento del Metal Criollo\r\n"
            		+ "Iorio fue cofundador y principal letrista de V8, la banda considerada la piedra angular del heavy metal en Argentina. Con álbumes crudos y pioneros como Luchando por el metal (1983), Un paso más en la batalla (1985) y El fin de los inicuos (1986), Iorio estableció una temática distintiva que, por primera vez, fusionó la potencia sonora del metal con letras cantadas en español que reflejaban la realidad social y espiritual de la juventud argentina.\r\n"
            		+ "\r\n"
            		+ "Hermética (1988-1994): El Culto y la Consagración\r\n"
            		+ "Tras la disolución de V8, Iorio fundó Hermética, logrando un éxito masivo y de culto. Junto a Claudio O'Connor y Antonio Romano, la banda pulió su sonido y elevó la profundidad lírica, tocando temas como la corrupción, la marginación y la conciencia de clase en discos esenciales como Ácido argentino (1991) y Víctimas del vaciamiento (1994). Hermética se convirtió en un fenómeno de masas, cimentando el estatus de Iorio como voz de los desposeídos y pensador del rock.\r\n"
            		+ "\r\n"
            		+ "Almafuerte (1995-2016): La Fusión Criolla\r\n"
            		+ "Al separarse Hermética, Iorio formó Almafuerte, proyecto que significó la madurez de su propuesta al incorporar decididamente elementos del folclore y la milonga argentina. Álbumes como Mundo guanaco (1995) y Toro y Pampa (2006) cimentaron un estilo único, con Iorio asumiendo el rol de voz principal en muchos temas y explorando la figura del gaucho, el honor y la defensa de la identidad nacional con un metal más cadencioso y rockero.\r\n"
            		+ "\r\n"
            		+ "Iorio Solista (1997-2023): La Obra Personal\r\n"
            		+ "Paralelamente a Almafuerte, Iorio desarrolló una prolífica carrera solista. Desde el seminal Peso argento (1997, junto a Flavio Cianciarulo) hasta trabajos como Atesorando en los Cielos (2015), sus discos en solitario le permitieron experimentar con otros géneros, interpretar tangos y milongas, y ahondar en reflexiones personales, siempre manteniendo su sello inconfundible y su presencia de figura mítica e ineludible en la cultura rock argentina.\r\n"
            		+ "\r\n"
            		+ "Iorio dejó un legado monumental que trasciende lo musical, siendo recordado como un poeta brutalmente honesto, un visionario del metal y un custodio de la identidad cultural argentina en el rock."; // Reemplaza con tu texto largo.
            String introduccionCorta ="\"Ricardo Iorio (1962 – 2023) fue la voz fundamental y letrista icónico del heavy metal argentino. Cofundador de V8, Hermética y Almafuerte, su legado trasciende lo musical, erigiéndose como un poeta de lo marginal y un pensador de la identidad criolla. Su obra es la crónica más cruda y honesta del rock nacional.\"";
            Biografia biografia = Biografia.builder()
                .biografiaCompleta(biografiaCompletaTexto)
                .introduccionCorta(introduccionCorta)
                .imagenFondoUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309127/iorio_bajo_juventud_gsnzzf.jpg") // URL de Cloudinary
                .imagenCard1Url("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309127/ricardo-iorio-v8_no_se_rindan_hwpo0v.webp")
                .artista(iorio) // Enlazamos al objeto Artista YA GUARDADO
                .build();
                
            biografiaRepository.save(biografia);


            // ==========================================================
            // C. CREACION DE  DISCOS (UNO A MUCHOS) - USANDO BUILDER
            // ==========================================================
            Disco luchandoPorElMetal = Disco.builder()
                .nombreDisco("Luchando por el metal")
                .listaTemas("1. Destrucción, 2. Parcas sangrientas, 3. Si puedes vencer al temor, 4. Ángel de las tinieblas, 5. Tiempos metálicos, 6. Muy cansado estoy, 7. Brigadas metálicas, 8. Torturador, 9. Hiena del metal") // Lista de temas
                .anio("1983")
                .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309270/V8_-_Luchando_por_el_metal_uucpwh.png") 
                .artista(iorio) // Enlazamos al Artista
                .build();
            	discoRepository.save(luchandoPorElMetal);

            Disco unPasoMasEnLaBatalla = Disco.builder()
                .nombreDisco("Un paso más en la batalla")
                .listaTemas("1. Deseando destruir y matar, 2. Servios del mal, 3. La mano maldita, 4. Cautivos del sistema, 5. Lanzado al mundo hoy, 6. Ideando la fuga, 7. Camino al sepulcro, 8. Momento de lucha") 
                .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309269/unPasoMasEnLaBatalla_c80e6j.webp")
                .anio("1985")
                .artista(iorio)
                .build();
            	discoRepository.save(unPasoMasEnLaBatalla);
            
            Disco elFinDeLosInicuos = Disco.builder()
                    .nombreDisco("El fin de los inicuos")
                    .listaTemas("1. La gran ramera, 2. Ciega ambición, 3. No enloqueceré, 4. El vivo sustento del inquisidor, 5. Antes que los viejos reyes, 6. Salmo nro.58, 7. El fin de los inicuos, 8. Trágico siglo, 9. Reina ciega") 
                    .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309270/V8_-_El_fin_de_los_inicuos_qwhzzp.png")
                    .anio("1986")
                    .artista(iorio)
                    .build();
                discoRepository.save(elFinDeLosInicuos);
            
            Disco hermeticaInterpretes  = Disco.builder()
                    .nombreDisco("Hermética/Intérpretes")
                    .listaTemas("1. Cráneo candente, 2. Masa anestesiada, 3. Desterrando a los oscurantistas, 4. Víctimas del vaciamiento, 5. Tú eres su seguridad, 6. Sepulcro civil, 7. Vida impersonal, 8. Desde el oeste, 9. Para que no caigas, 10. Deja de robar, 11. Yo no lo haré, 12. Vencedores vencidos, 13. Ideando la fuga, 14. Cambalache, 15. Destrucción, 16. No class, 17. Porque hoy nací") 
                    .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309255/hermetica_zj2xvj.webp")
                    .anio("1989")
                    .artista(iorio)
                    .build();
                discoRepository.save(hermeticaInterpretes);   
                
                
            Disco acidoArgentino  = Disco.builder()
                    .nombreDisco("Ácido Argentino")
                    .listaTemas("1. Robo un auto, 2. La revancha de América, 3. Memorria de siglos, 4. Predicción, 5. Atravesando todo límite, 6. Horizonte perdido, 7. Vientos de poder, 8. Del camionero, 9. Gil trabajador, 10. Evitando el ablande, 11. En las calles de Liniers, 12. De Pismanta a Baucacheta") 
                    .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309255/hermeticaAcidoArgentino_k4tyoe.webp")
                    .anio("1991")
                    .artista(iorio)
                    .build();
                discoRepository.save(acidoArgentino);
                
                
            Disco victimasDelVaciamiento  = Disco.builder()
                    .nombreDisco("Victimas del vaciamiento")
                    .listaTemas("1. Soy de la esquina, 2. Otro día para ser, 3. Traición, 4. Olvídalo y volverá por más, 5. Hospitalarias realidades, 6. Buscando razón, 7. Cuando duerme la ciudad, 8. Ayer deseo, hoy realidad, 9. Del colimba, 10. Moraleja, 11. Tano solo") 
                    .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309264/hermeticaVictimasDelVaciamiento_xf4krp.webp")
                    .anio("1994")
                    .artista(iorio)
                    .build();
                discoRepository.save(victimasDelVaciamiento);  
                
                Disco mundoGuanaco = Disco.builder()
                        .nombreDisco("Mundo guanaco")
                        .listaTemas("1. Dijo el droguero al drogador, 2. Desencuentro, 3. El pibe tigre, 4. Como los bueyes, 5. Voy a enloquecer, 6. El amasijo de un gran sueño, 7. De los pagos del tiempo, 8. Buitres, 9. Sentir indiano, 10. Zamba de resurección") 
                        .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309193/Almafuerte_mundoguanaco_tapa_tcgqpo.jpg")
                        .anio("1995")
                        .artista(iorio)
                        .build();
                    discoRepository.save(mundoGuanaco);
                    
                    
                Disco delEntorno  = Disco.builder()
                        .nombreDisco("Del entorno")
                        .listaTemas("1. Del entorno, 2. Lucero del alba, 3. Hacia el abismo, 4. Amistades de tierra adentro, 5. Los delirios del Defacto, 6. 1999, 7. De la carne, 8. Hombre Peste, 9. Ruben Patagonia, 10. Presa fácil") 
                        .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309256/almafuertedelentorno_fegb4z.webp")
                        .anio("1996")
                        .artista(iorio)
                        .build();
                    discoRepository.save(delEntorno);
                    
                    
                Disco enVida = Disco.builder()
                        .nombreDisco("En vida")
                        .listaTemas("1. De los pagos del tiempo, 2. El pie tigre, 3. Lanzado al mundo, 4. Atravesando todo límite, 5. Desencuentro, 6. 1999, 7. Buitres, 8. Por nacer, 9. Sentir indiano, 10 .Mal bicho, 11. De mandadores a mandados, 12. Gil trabajador, 13. Moraleja, 14. Ayer deseo hoy realidad, 15. Zamba de resurreción, 16. Dijo el droguero al drogador, 17. Por tu suerte, 18. Del más allá") 
                        .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309205/almafuerteenvida_zlhhia.webp")
                        .anio("1997")
                        .artista(iorio)
                        .build();
                    discoRepository.save(enVida);
                      
                Disco pesoArgento = Disco.builder()
                        .nombreDisco("Peso Argento")
                        .listaTemas("1. Allá en Tilcara, 2. Nacido y criado en el sur, 3. Río Paraná, 4. De mandinga y remolinos, 5. Mal bicho, 6. Ramón el indio hereje, 7. Cacique Yatel, 8. Gil trabajador, 9. Para pocos de los muchos, 10. De mandadores y mandados, 11. Virgen de los muertitos") 
                        .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309248/flavioioriopesoargento_k8nc0b.webp")
                        .anio("1997")
                        .artista(iorio)
                        .build();
                    discoRepository.save(pesoArgento);
                    
                Disco almaFuerte = Disco.builder()
                        .nombreDisco("Almafuerte")
                        .listaTemas("1. Mano brava, 2. Almafuerte, 3. Triunfo, 4. Sé vos, 5. Niño jefe, 6. Memoria de siglos, 7. Ser humano junto a los míos, 8. Desde el oeste, 9. Del más allá, 10. Tú eres su seguridad, 11. Ceibo") 
                        .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309205/almafuertealmafuerte_jnalk8.webp")
                        .anio("1998")
                        .artista(iorio)
                        .build();
                    discoRepository.save(almaFuerte);
                
                Disco aFondoBlanco = Disco.builder()
                        .nombreDisco("A fondo blanco")
                        .listaTemas("1. Homenaje, 2. Convide rutero, 3. Si me estas buscando, 4. A vos amigo, 5. Tangolpeando, 6. Aguante Bonavena, 7. El visitante, 8. Hoy es, 9. La llaga, 10. Al pájaro, 11.Motivo ciudadano") 
                        .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309199/Almafuerte_a_fondo_blanco_cover_xd0tpf.jpg")
                        .anio("1999")
                        .artista(iorio)
                        .build();
                    discoRepository.save(aFondoBlanco);
            
                Disco piedraLibre = Disco.builder()
                            .nombreDisco("Piedra Libre")
                            .listaTemas("1. Las aguas turbias suben esta vez, 2. Orgullo argentino, 3. Por ser yo, 4. Alli en San Juan, 5.Amanecer en Open Door, 6. Regresando, 7. De un mañana bajo tierra, 8. Sirva otra vuelta pulpero, 9. Cumpliendo mi destino, 10. Para todos mis compañeros") 
                            .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309210/almafuertepiedralibre_dyma4i.webp")
                            .anio("2001")
                            .artista(iorio)
                            .build();
                        discoRepository.save(piedraLibre);
                        
                        
                Disco enVivoObras2001 = Disco.builder()
                            .nombreDisco("En vivo Obras 2001")
                            .listaTemas("1. Introducción, 2. Almafuerte, 3. Las aguas turbias subes esta vez, 4. Homenaje, 5. Hombre peste, 6. 1999, 7. Convide rutero, 8. El visitante, 9. Los delirios del defacto, 10. Amancer en Open Door, 11. Del más allá, 12. De una mañana bajo tierra, 13. El pibe tigre, 14. Lucero del alba, 15. A vos amigo, 16. Popurrí, 17. Libre de temor") 
                            .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309212/almafuertevivoobras2001_w2rrzj.webp")
                            .anio("2001")
                            .artista(iorio)
                            .build();
                        discoRepository.save(enVivoObras2001);        
                        
                            
                Disco ultimando  = Disco.builder()
                            .nombreDisco("Ultimando")
                            .listaTemas("1. Patria al hombro, 2. Ultranza, 3. Todo es en vano si no hay amor, 4. Con rumbo al Abra, 5. Ruta 76, 6. Del fumador, 7. En este viaje, 8. Yo traigo la semilla, 9. T.C, 10. Sojuzgados y sometidos, 11. Como estaba ahí Dios") 
                            .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309193/Almafuerte_ultimando_album_cover_guqhcu.jpg")
                            .anio("2003")
                            .artista(iorio)
                            .build();
                        discoRepository.save(ultimando);
                        
                        
                Disco diezAnios = Disco.builder()
                            .nombreDisco("1995-2005 10 Años")
                            .listaTemas("1. Todo es vano si no hay amor, 2. Con rumbo al abra, 3. Patria al hombro, 4. Mano brava, 5. Ruta 76, 6. Si me estás buscando, 7. Como estaba ahí Dios, 8.Tangolpeando, 9. La llaga, 10. Allá en Tílcara, 11. Para pocos de los muchos, 12. Sé vos, 13. Yo traigo la semilla, 14. Sentir Indiano, 15.1999, 16. Por el sudaca, 17. En este viaje, 18. Hoy es, 19. Ser humano junto a los míos, 20. Convide rutero, 21. Almafuerte") 
                            .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309201/almafuerte10anios_dd8r6p.webp")
                            .anio("2005")
                            .artista(iorio)
                            .build();
                        discoRepository.save(diezAnios);
                        
                Disco toroYPampa = Disco.builder()
                            .nombreDisco("Toro y Pampa")
                            .listaTemas("1. Debes saberlo, 2. Pensando en llegar, 3. De la escuelita, 4. Unas estrofas más, 5. La máquina de picar carne, 6. Donde está mi corazón, 7. En el siglo del gran reviente, 8. Vencer El tiempo, 9. Toro y pampa, 10. Cosas que pueden pasar, 11. Sopla el Pampero, 12. Sé vos (acústico), 13. Por ser yo (en vivo en obras 2001)") 
                            .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309210/almafuertetoroypampa_bg9wgf.webp")
                            .anio("2006")
                            .artista(iorio)
                            .build();
                        discoRepository.save(toroYPampa);       
                        
                Disco ayerDeseoHoyRealidad = Disco.builder()
                            .nombreDisco("Ayer deseo, hoy realidad")
                            .listaTemas("1. Hace casi 2000 años, 2. Toma del tren hacia el sur, 3. Blues del atardecer, 4. Solitario Juan, 5. Ritmo y blues con armonicas, 6. Durazno sangrado, 7. Jugo de tomate, 8. Un amigo de verdad, 9. Rock de la madre selva, 10. Tontos, 11. Vine cruzando el mar, 12. Imágenes fugaces, 13. Mariposas de madera") 
                            .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309196/iorioayerdesohoyrealidad_nvmrdo.webp")
                            .anio("2008")
                            .artista(iorio)
                            .build();
                        discoRepository.save(ayerDeseoHoyRealidad);
                        
                        
                Disco enVivoObras = Disco.builder()
                           .nombreDisco("En vivo Obras")
                           .listaTemas("1. El amasijo de un gran sueño, 2. Niño jefe, 3. Todo es en vano si no hay amor, 4. Debes aberlo, 5. Yo traigo la semilla, 6. Toro y pampa, 7. Sé vos, 8. Triunfo, 9. Patria al hombro, 10. De pie, 11. Pensando en llegar, 12. La máquinade picar carne, 13. Donde está mi corazón, 14. Homenaje, 15. El pibe tigre, 16. A vos amigo, 17. De la carne, 18. Sirva otra vuelta pulpero") 
                           .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309210/almafuerteobras2009_femsyb.webp")
                           .anio("2009")
                           .artista(iorio)
                           .build();
                        discoRepository.save(enVivoObras);          
                        
                Disco trillandoLaFina = Disco.builder()
                           .nombreDisco("Trillando la fina")
                           .listaTemas("1. Muere monstruo muere, 2. Trillando la fina, 3. Pal´Pelusa, 4. Si me ves volver, 5. Pal´recuerdo, 6. Ciudad de Rosario, 7. Mi credo, 8. La llaga, 9. Glifosateando, 10. Mamuil Mapu, 11. Caballo Negro") 
                           .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309211/almafuertetrillandolafina_yds3xw.webp")
                           .anio("2012")
                           .artista(iorio)
                           .build();
                        discoRepository.save(trillandoLaFina);             
                            
                Disco tangosYMilongas = Disco.builder()
                           .nombreDisco("Tangos y Milongas")
                           .listaTemas("1. El adiós de Gabino Ezeiza, 2. Martirio, 3. Sabe Don, 4. El último viaje, 5. En un feca, 6. Más allá, 7. Y a mi que, 8. Vieja recova, 9. No te apures Carablanca, 10. En la vía, 11. Tangolpeando, 12. Gol argentino, 13. No la quiero más") 
                           .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309199/ricardoioriotangos_jyl8th.webp")
                           .anio("2014")
                           .artista(iorio)
                           .build();
                        discoRepository.save(tangosYMilongas);          
                            
                            
                            
                Disco atesorandoEnLosCielos  = Disco.builder()
                           .nombreDisco("Atesorando en los cielos")
                           .listaTemas("1. Guitarrera, 2. Preguntando lo que todos saben, 3. Robo un auto, 4. Quiero ser como usted, 5. The krochik, 6. Justo que te vas, 7. No me cambiarán, 8. De mi rumbear al Sur, 9. Ideando la fuga, 10. Otro día para ser, 11. Uno") 
                           .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309195/iorioatesorandoenloscielos_zkkbw0.webp")
                           .anio("2015")
                           .artista(iorio)
                           .build();
                        discoRepository.save(atesorandoEnLosCielos);
                                               
                Disco avivandoLaLlamaDeLaLeyNatural = Disco.builder()
                           .nombreDisco("Avivando la llama de la ley natural")
                           .listaTemas("1. De Pismanta a Bauchaceta, 2. Para que no caigas, 3. Desde el Oeste, 4. El oficio del cantor, 6. Yo no lo haré, 7. En las calles de Liniers, 8. Cuando duerme la ciudad, 9. Hombres de hierro, 10. Para vos, 11. Diferencias, 12. Vida impersonal, 13. Del camionero, 14. Gol argentino, 15. Tangolpeando, 16. Cautivos del sistema, 17. Quien quiera oir que oiga, 18. Morir al lado de mi amor, 19. Me gusta la gente simple, 20. Horizonte perdido") 
                           .imagenUrl("https://res.cloudinary.com/dpytht2dn/image/upload/v1760309193/avivandoLaLLAmaDeLaLeyNatural_cmwucm.jpg")
                           .anio("2022")
                           .artista(iorio)
                           .build();
                        discoRepository.save( avivandoLaLlamaDeLaLeyNatural);
                            
             
            
            
            // ==========================================================
            // D. CREAR VIDEOS (UNO A MUCHOS) - USANDO BUILDER
            // ==========================================================
            Video destruccionV8 = Video.builder()
                .titulo("Destrucción-V8") 
                .youtubeUrl("https://youtu.be/ywCC5dxL5hs?si=OcllFKDQF5yCGYAF") // URL de YouTube que recopilada
                .descripcion("Tema correspondiente al álbum debut de V8-Luchando por el metal")
                .artista(iorio) // Enlazamos al Artista
                .disco(luchandoPorElMetal)
                .build();
            videoRepository.save(destruccionV8);
            
            Video brigadasMetalicas = Video.builder()
            		.titulo("Brigadas Metálicas-V8")
            		.youtubeUrl("https://youtu.be/FdFwCn6zHLA?si=fs_T1Qxz9fh1h0Ke")
            		.descripcion("Tema correspondiente al primer álbum de V8")
            		.artista(iorio)
            		.disco(luchandoPorElMetal)
            		.build();
            videoRepository.save(brigadasMetalicas);
            
            Video vientosDePoder = Video.builder()
            		.titulo("Vientos de poder-Hermética")
            		.youtubeUrl("https://youtu.be/GsM2V4guZ_s?si=g-iMZa37oVGYh-Et")
            		.descripcion("Tema del primer trabajo discográfico de la banda Hermética")
            		.artista(iorio)
            		.disco(hermeticaInterpretes)
            		.build();
            videoRepository.save(vientosDePoder);
            
            
            Video laRevanchaDeAmerica = Video.builder()
            		.titulo("La revancha de América-Hermética")
            		.youtubeUrl("https://youtu.be/4CiaDZMmBDg?si=CIUIgjAQ3LePARV8")
            		.descripcion("Tema correspondiente a la primer placa discográfica de Hermética")
            		.artista(iorio)
            		.disco(hermeticaInterpretes)
            		.build();
            videoRepository.save(laRevanchaDeAmerica);
            
            Video gilTrabajador = Video.builder()
            		.titulo("Gil trabajador-Hermetica")
            		.youtubeUrl("https://youtu.be/sKDjh5t4FnM?si=LcYwA5V_OWQvnUcu")
            		.descripcion("Tema correspondiente al segundo trabajo discográfico de Hermética")
            		.artista(iorio)
            		.disco(acidoArgentino)
            		.build();
            videoRepository.save(gilTrabajador);
            
            Video elPibeTigre = Video.builder()
            		.titulo("El pibe Tigre-Almafuerte")
            		.youtubeUrl("https://youtu.be/jw3FOeNtajg?si=8iU9qEU_OWw5WtbL")
            		.descripcion("Tema correspondiente a la placa Mundo Guanaco")
            		.artista(iorio)
            		.disco(mundoGuanaco)
            		.build();
            videoRepository.save(elPibeTigre);
            
            
            Video amistadesDeTierraAdentro = Video.builder()
            		.titulo("Amistades de Tierra adentro-Almafuerte")
            		.youtubeUrl("https://youtu.be/A_RBA3Ce7_I?si=CjA-4idAk74KuLwe")
            		.descripcion("Tema correspondiente al disco del entorno")
            		.artista(iorio)
            		.disco(delEntorno)
            		.build();
            videoRepository.save(amistadesDeTierraAdentro);
            
            Video aVosAmigo = Video.builder()
            		.titulo("A vos amigo-Almafuerte")
            		.youtubeUrl("https://youtu.be/ItQoKaeohEI?si=cohLZGcGszs6ukHT")
            		.descripcion("Tema correspondiente a la placa A fondo blanco")
            		.artista(iorio)
            		.disco(aFondoBlanco)
            		.build();
            
            videoRepository.save(aVosAmigo);
            
            Video triunfo = Video.builder()
            		.titulo("Triunfo-Almafuerte")
            		.youtubeUrl("https://youtu.be/uIgAWaKAurg?si=RMdJg1dmw2U2zuVx")
            		.descripcion("Tema del famoso disco de las cartas de almafuerte")
            		.artista(iorio)
            		.disco(almaFuerte)
            		.build();
            videoRepository.save(triunfo);
            
            Video justoQueTeVas = Video.builder()
            		.titulo("Justo que te vas-Iorio")
            		.youtubeUrl("https://youtu.be/KowXSi2ILRw?si=BYtxb84oAWUMGQjs")
            		.descripcion("Tema solista de Iorio")
            		.artista(iorio)
            		.disco(atesorandoEnLosCielos)
            		.build();
            videoRepository.save(justoQueTeVas);
            
            Video himnoArgentino = Video.builder()
            		.titulo("Himno Argentino")
            		.youtubeUrl("https://youtu.be/08TNhQlTxhw?si=gnfTNQm7T8UllIjo")
            		.descripcion("Himno Argentino interpretado por Ricardo Iorio, en lo que iba a ser su presentación en la previa de un partido de la selección, evento que finalmente no fue realizado")
            		.artista(iorio)
            		.disco(avivandoLaLlamaDeLaLeyNatural)
            		.build();
            videoRepository.save(himnoArgentino);
            
            Video entrevistaAtc = Video.builder()
            		.titulo("Entrevista post hermética parte 1")
            		.youtubeUrl("https://youtu.be/rSaQYGLyT00?si=4lgavH1jsunmIhzi")
            		.descripcion("Entrevista post separacion de Hermetica, inicios de Almafuerte")
            		.artista(iorio)
            		.disco(mundoGuanaco)
            		.build();
            
            videoRepository.save(entrevistaAtc);
            
            Video entrevistaRockPolitik = Video.builder()
            		.titulo("Entrevista en Rock politico")
            		.youtubeUrl("https://youtu.be/ta0oUQwHC0E?si=XpCZ3xRW2WBIe1r9")
            		.descripcion("Entrevista donde queda inmortalizada la frase: generalmente hay que morirse para que te quieran ")
            		.artista(iorio)
            		.disco(mundoGuanaco)
            		.build();
            
            videoRepository.save(entrevistaRockPolitik);
            
            Video short1 = Video.builder()
            		.titulo("Si jesus...")
            		.youtubeUrl("https://youtube.com/shorts/PnjVZfT31tw?si=5zOd6nfFvj_9524l")
            		.descripcion("Video Short frases destacables parte 1")
            		.artista(iorio)
            		.disco(avivandoLaLlamaDeLaLeyNatural)
            		.build();
            
            videoRepository.save(short1);
            
            Video short2 = Video.builder()
            		.titulo("Si en 17 años un joven...")
            		.youtubeUrl("https://youtube.com/shorts/AuV3zyHNyvQ?si=-NWC_rewFU9xZ6pG")
            		.descripcion("Iorio en época de Hermética dando el fundamento y el porque de la banda")
            		.artista(iorio)
            		.disco(hermeticaInterpretes)
            		.build();
            
            videoRepository.save(short2);
            
            Video short3 = Video.builder()
            		.titulo("Ser un metalero pesado..")
            		.youtubeUrl("https://youtube.com/shorts/XdPTwb_x_Ww?si=PCu8CRNrC5NnxOLP")
            		.descripcion("Apreciación sobre qué es ser un metalero pesado")
            		.artista(iorio)
            		.disco(mundoGuanaco)
            		.build();
            videoRepository.save(short3);
            
            
            
                 
            

            
           


            System.out.println("-> Carga de datos inicial completa y exitosa.");
        } else {
             System.out.println("-> La base de datos ya contiene datos. Inicialización omitida.");
        }
    }

}
