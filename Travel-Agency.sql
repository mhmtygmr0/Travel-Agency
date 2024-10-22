PGDMP      %                |            travel_agency    15.7    16.3 5    5           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            6           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            7           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            8           1262    18143    travel_agency    DATABASE     �   CREATE DATABASE travel_agency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE travel_agency;
                postgres    false            �            1259    18175    hotel    TABLE     E  CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text,
    hotel_email text,
    hotel_phone text,
    hotel_star text,
    car_park boolean,
    wifi boolean,
    pool boolean,
    fitness boolean,
    concierge boolean,
    spa boolean,
    room_service boolean
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    18174    hotel_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hotel_hotel_id_seq;
       public          postgres    false    217            9           0    0    hotel_hotel_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.hotel_hotel_id_seq OWNED BY public.hotel.hotel_id;
          public          postgres    false    216            �            1259    18263    pension_pension_id_seq    SEQUENCE        CREATE SEQUENCE public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.pension_pension_id_seq;
       public          postgres    false            �            1259    18195    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id integer DEFAULT nextval('public.pension_pension_id_seq'::regclass) NOT NULL,
    pension_type text NOT NULL,
    hotel_id integer NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false    225            �            1259    18230    reservation    TABLE     w  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    room_id integer NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price double precision NOT NULL,
    guest_count integer NOT NULL,
    guest_name text NOT NULL,
    guest_citizen_id text NOT NULL,
    guest_phone text NOT NULL,
    guest_email text NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    18229    reservation_reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.reservation_reservation_id_seq;
       public          postgres    false    224            :           0    0    reservation_reservation_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.reservation_reservation_id_seq OWNED BY public.reservation.reservation_id;
          public          postgres    false    223            �            1259    18208    room    TABLE     .  CREATE TABLE public.room (
    room_id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer,
    season_id integer NOT NULL,
    room_type character varying(20) NOT NULL,
    stock integer NOT NULL,
    adult_price numeric(10,2) NOT NULL,
    child_price numeric(10,2) NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL,
    projection boolean NOT NULL,
    gym boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    18207    room_room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.room_room_id_seq;
       public          postgres    false    222            ;           0    0    room_room_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.room_room_id_seq OWNED BY public.room.room_id;
          public          postgres    false    221            �            1259    18184    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    hotel_id integer,
    start_date date,
    end_date date,
    price_parameter double precision
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    18183    season_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.season_season_id_seq;
       public          postgres    false    219            <           0    0    season_season_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.season_season_id_seq OWNED BY public.season.season_id;
          public          postgres    false    218            �            1259    18168    user    TABLE       CREATE TABLE public."user" (
    user_id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    role character varying(20)
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    18167    user_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.user_user_id_seq;
       public          postgres    false    215            =           0    0    user_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;
          public          postgres    false    214                       2604    18178    hotel hotel_id    DEFAULT     p   ALTER TABLE ONLY public.hotel ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotel_hotel_id_seq'::regclass);
 =   ALTER TABLE public.hotel ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    18233    reservation reservation_id    DEFAULT     �   ALTER TABLE ONLY public.reservation ALTER COLUMN reservation_id SET DEFAULT nextval('public.reservation_reservation_id_seq'::regclass);
 I   ALTER TABLE public.reservation ALTER COLUMN reservation_id DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    18211    room room_id    DEFAULT     l   ALTER TABLE ONLY public.room ALTER COLUMN room_id SET DEFAULT nextval('public.room_room_id_seq'::regclass);
 ;   ALTER TABLE public.room ALTER COLUMN room_id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    18187    season season_id    DEFAULT     t   ALTER TABLE ONLY public.season ALTER COLUMN season_id SET DEFAULT nextval('public.season_season_id_seq'::regclass);
 ?   ALTER TABLE public.season ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    219    218    219            ~           2604    18171    user user_id    DEFAULT     n   ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);
 =   ALTER TABLE public."user" ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    215    214    215            *          0    18175    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_email, hotel_phone, hotel_star, car_park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    217   �A       -          0    18195    pension 
   TABLE DATA           E   COPY public.pension (pension_id, pension_type, hotel_id) FROM stdin;
    public          postgres    false    220   �B       1          0    18230    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_citizen_id, guest_phone, guest_email) FROM stdin;
    public          postgres    false    224   �C       /          0    18208    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_id, pension_id, season_id, room_type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection, gym) FROM stdin;
    public          postgres    false    222   'D       ,          0    18184    season 
   TABLE DATA           \   COPY public.season (season_id, hotel_id, start_date, end_date, price_parameter) FROM stdin;
    public          postgres    false    219   iE       (          0    18168    user 
   TABLE DATA           Z   COPY public."user" (user_id, username, password, first_name, last_name, role) FROM stdin;
    public          postgres    false    215   �E       >           0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 14, true);
          public          postgres    false    216            ?           0    0    pension_pension_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.pension_pension_id_seq', 48, true);
          public          postgres    false    225            @           0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 14, true);
          public          postgres    false    223            A           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 35, true);
          public          postgres    false    221            B           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 35, true);
          public          postgres    false    218            C           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 5, true);
          public          postgres    false    214            �           2606    18182    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    217            �           2606    18257    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    220            �           2606    18237    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    224            �           2606    18213    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    222            �           2606    18189    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    219            �           2606    18173    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    215            �           2606    18280    room hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id) ON DELETE CASCADE;
 <   ALTER TABLE ONLY public.room DROP CONSTRAINT hotel_id_fkey;
       public          postgres    false    217    222    3207            �           2606    18270    pension pension_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id) ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_hotel_id_fkey;
       public          postgres    false    217    3207    220            �           2606    18275    room pension_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT pension_id_fkey FOREIGN KEY (pension_id) REFERENCES public.pension(pension_id) ON DELETE CASCADE;
 >   ALTER TABLE ONLY public.room DROP CONSTRAINT pension_id_fkey;
       public          postgres    false    222    220    3211            �           2606    18305 $   reservation reservation_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(room_id) ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_room_id_fkey;
       public          postgres    false    224    3213    222            �           2606    18290    room room_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.room DROP CONSTRAINT room_hotel_id_fkey;
       public          postgres    false    3207    222    217            �           2606    18295    room room_pension_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pension_id_fkey FOREIGN KEY (pension_id) REFERENCES public.pension(pension_id) ON DELETE CASCADE;
 C   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pension_id_fkey;
       public          postgres    false    222    3211    220            �           2606    18300    room room_season_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_season_id_fkey FOREIGN KEY (season_id) REFERENCES public.season(season_id) ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.room DROP CONSTRAINT room_season_id_fkey;
       public          postgres    false    3209    222    219            �           2606    18265    season season_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id) ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.season DROP CONSTRAINT season_hotel_id_fkey;
       public          postgres    false    219    3207    217            �           2606    18285    room season_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT season_id_fkey FOREIGN KEY (season_id) REFERENCES public.season(season_id) ON DELETE CASCADE;
 =   ALTER TABLE ONLY public.room DROP CONSTRAINT season_id_fkey;
       public          postgres    false    222    3209    219            *   �   x�uQ�N�0<o�"���k;��H\�K�rYHi��D�����G�ð�bd���Yifg�ձ9:j��5��>O��swrd���s��RZ�
9lq]�����ۑ��К/y�R#$�
�Ƒ��ć3=�P�RH�,��~���2�q���(�.%�NG`P�<��B���V�ƥ���H�p��z3@=]?-A��|0.�R���`��&��Z���짷�w�ֿ��G�a~���Ͳ��p��      -   �   x�}QAn� <3���]c�H[�JM�`E�*��_t�C������3�Qq	?�2ܔ+B��A��|����g!N=�4�-e�q�!���_��6'y�����[ġWk�_���s��$��{að����S�EYX>5`lS-iXW���������:�pTs�+Opsh�u�A��A�'�����}�muL��G{�T-R��Cn��%B�6����u��t      1   Z   x�34�4��4202�50�50�2-@LCsc3N#NǜLN#$�i� ��9�ɉy鹉�9z���\�&��&842� �������������� �{!�      /   2  x�}��n�0����v�u�41mEj�mRo<��_�Ch��&��O���|yp�:�/�o��4}��O]�~>����q��?u/�ҁ�:}<�����$`�z|̗q��o���Q-�1$dl�LG��=H�õMU&=`�n�ku�Yu�1�E�����A�%��l�
�M�B����"(�L\[S�K!�tRl�ql���]�r*�p����mf��UҴ��Ss=B��ek̹�")b���~4@������ԗ��!�O���@��m=n$<���z�-��8��\���r������hs/���s���P      ,      x�}�Q
!D���t�L��ޥ�?G���VS�c�A&/4��C�XG%�������	lJ���S��Q�E������	�����bM�f���\���V��jC9�~i��hCcm�*�im~��,�P�      (   �   x�3��M��M-�4 N_0[!81��32�����"�Ĕ��<.#ΔԼ��T�:(J����ps&�dBT8�d&'�qz'V&"�M8�R3�!*��L����"�
S���{�`J�!�#r2�f���qqq h�;�     