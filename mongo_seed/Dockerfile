FROM mongo

COPY init.json /init.json
CMD mongoimport --host mongo_db --db purplecowdb --collection items --type json --file /init.json --jsonArray