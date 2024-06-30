def check_is_data_null(data):
    return data if data is not None else 0

def calculate_best_rating(rating, current_best_rating):
    return current_best_rating if float(check_is_data_null(rating)) < current_best_rating else float(check_is_data_null(rating))

def calculate_percent(attempts, success):
    return round(success/attempts*100, 2) if attempts else 0