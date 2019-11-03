import csv
import random

def generateMovieIds():
    movieIds = set()
    for x in range(0, 100):
        movieIds.add(random.randint(1, 185077))
    return movieIds

movie_data_file = open("../data/b_movie-data.sql", "w")
movie_data_file.write("USE movie_history;\n")
movie_data_file.write("BEGIN;\n")

print("Creating commands for adding movies to the database...")
with open('movies.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=';')
    for row in csv_reader:
        movie_data_file.write(f'INSERT INTO Movie (Title, Year) VALUES ("{row[0]}", "{row[1]}");\n')
    
print("Done!")

movie_data_file.write("COMMIT;\n")
movie_data_file.close()

user_data_file = open("../data/c_user-data.sql", "w")
user_data_file.write("USE movie_history;\n")
user_data_file.write("BEGIN;\n")

print("Creating commands for adding users to the database...")
userCounter = 1
with open('users.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=';')
    for row in csv_reader:
        # Add the user to the database
        user_data_file.write(f'INSERT INTO User (Lastname, Firstname, Email, Password) VALUES ("{row[0]}", "{row[1]}", "{row[2]}", "{row[3]}");\n')

        # Generate some movies that he has seen
        for movieId in generateMovieIds():
            user_data_file.write(f'INSERT INTO User_has_seen_Movie (User_idUser, Movie_idMovie) VALUES ({userCounter}, {movieId});\n')
        userCounter += 1


print("Done!")

user_data_file.write("COMMIT;\n")
user_data_file.close()
