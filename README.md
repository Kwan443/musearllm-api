# musearllm-api
open postgreSQL

-- Create the database (if it doesn't exist)
CREATE DATABASE art_museum_db;

-- Connect to the database (this would be done in your client/application)
-- \c art_museum_db

-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create Museum table first (no dependencies)
CREATE TABLE Museum (
    museum_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL,
    operating_hours JSONB,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Artist table (no dependencies)
CREATE TABLE Artist (
    artist_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    biography TEXT,
    birth_date DATE,
    death_date DATE,
    nationality VARCHAR(100),
    wikipedia_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for Artist table
CREATE INDEX idx_artist_name ON Artist(name);
CREATE INDEX idx_artist_nationality ON Artist(nationality);

-- Create Gallery table (depends on Museum)
CREATE TABLE Gallery (
    gallery_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    museum_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    floor_number VARCHAR(50),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (museum_id) REFERENCES Museum(museum_id) ON DELETE CASCADE
);

-- Create indexes for Gallery table
CREATE INDEX idx_gallery_museum ON Gallery(museum_id);
CREATE INDEX idx_gallery_name ON Gallery(name);

-- Create Artwork table (depends on Artist and Gallery)
CREATE TABLE Artwork (
    artwork_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    artist_id UUID NOT NULL,
    current_gallery_id UUID,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    history TEXT,
    year_created INTEGER,
    medium VARCHAR(255),
    dimensions VARCHAR(100),
    origin VARCHAR(100),
    has_ar_assets BOOLEAN DEFAULT FALSE,
    wikipedia_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (artist_id) REFERENCES Artist(artist_id) ON DELETE CASCADE,
    FOREIGN KEY (current_gallery_id) REFERENCES Gallery(gallery_id) ON DELETE SET NULL
);

-- Create indexes for Artwork table
CREATE INDEX idx_artwork_title ON Artwork(title);
CREATE INDEX idx_artwork_artist ON Artwork(artist_id);
CREATE INDEX idx_artwork_gallery ON Artwork(current_gallery_id);
CREATE INDEX idx_artwork_year ON Artwork(year_created);
CREATE INDEX idx_artwork_ar_assets ON Artwork(has_ar_assets);

-- Create function to update updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create triggers to automatically update updated_at
CREATE TRIGGER update_museum_updated_at
    BEFORE UPDATE ON Museum
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_artist_updated_at
    BEFORE UPDATE ON Artist
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_gallery_updated_at
    BEFORE UPDATE ON Gallery
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_artwork_updated_at
    BEFORE UPDATE ON Artwork
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Optional: Create some sample data
INSERT INTO Museum (name, address, operating_hours, description) VALUES
(
    'Metropolitan Museum of Art',
    '1000 5th Ave, New York, NY 10028',
    '{"monday": {"open": "10:00", "close": "17:30"}, "tuesday": {"open": "10:00", "close": "17:30"}, "wednesday": {"open": "10:00", "close": "17:30"}, "thursday": {"open": "10:00", "close": "17:30"}, "friday": {"open": "10:00", "close": "21:00"}, "saturday": {"open": "10:00", "close": "17:30"}, "sunday": {"open": "10:00", "close": "17:30"}}'::jsonb,
    'The Metropolitan Museum of Art presents over 5,000 years of art from around the world.'
);

INSERT INTO Artist (name, biography, birth_date, death_date, nationality, wikipedia_url) VALUES
(
    'Vincent van Gogh',
    'Dutch Post-Impressionist painter who is among the most famous and influential figures in the history of Western art.',
    '1853-03-30',
    '1890-07-29',
    'Dutch',
    'https://en.wikipedia.org/wiki/Vincent_van_Gogh'
);

-- Get the museum_id for the inserted museum
DO $$
DECLARE
    v_museum_id UUID;
    v_artist_id UUID;
BEGIN
    SELECT museum_id INTO v_museum_id FROM Museum WHERE name = 'Metropolitan Museum of Art';
    SELECT artist_id INTO v_artist_id FROM Artist WHERE name = 'Vincent van Gogh';
    
    -- Insert Gallery
    INSERT INTO Gallery (museum_id, name, floor_number, description) VALUES
    (
        v_museum_id,
        'European Paintings Gallery',
        '2',
        'Gallery featuring European paintings from the 19th century'
    )
    RETURNING gallery_id INTO v_museum_id;
    
    -- Insert Artwork
    INSERT INTO Artwork (artist_id, current_gallery_id, title, description, year_created, medium, dimensions, origin, has_ar_assets, wikipedia_url) VALUES
    (
        v_artist_id,
        v_museum_id,
        'The Starry Night',
        'A post-impressionist painting of a night sky over a small town',
        1889,
        'Oil on canvas',
        '73.7 cm × 92.1 cm',
        'Netherlands',
        TRUE,
        'https://en.wikipedia.org/wiki/The_Starry_Night'
    );
END $$;



run in intellij 

change SQL setting in src\main\resources\application-main.yml
datasource:
    url: jdbc:postgresql://localhost:5432/Your_db <--------------------------
    username: postgres //should be same
    password: Your_pw <------------------------------------------------------
    driver-class-name: org.postgresql.Driver

mvn clean install  
or
mvnd clean install  

run src/main/java/com/musearllm/api/MuseARLLMApplication.java by intellij 

