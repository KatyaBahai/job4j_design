package ru.job4j.lsperrors;

class SocialMedia {
}

class Vkontakte extends SocialMedia {
}

class Facebook extends SocialMedia {
}

class Telegram extends SocialMedia {
}

class SocialMediaActivation {
    public void activate(SocialMedia media) {
        if (media instanceof Vkontakte) {
            System.out.println("Log in vk.com");
        } else if (media instanceof Facebook) {
            System.out.println("Log in vk.com");
        } else if (media instanceof Telegram) {
            System.out.println("Log in vk.com");
        }
    }
}

