import Splitwise

class User:
    user_count = 0
    def __init__(self, name, email, mobile):
        User.user_count+=1
        self.userid = "u"+str(User.user_count)
        self.name =  name
        self.email = email
        self.mobile = mobile
    
    def get_name(self):
        return self.name
    
    def get_email(self):
        return self.email
    
    def get_mobile(self):
        return self.mobile
