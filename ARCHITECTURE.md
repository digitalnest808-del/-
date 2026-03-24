# معمارية مشروع شات زهراء

هذا المستند يشرح معمارية المشروع والتصميم الداخلي.

## نمط المعمارية: MVVM

يستخدم المشروع نمط **Model-View-ViewModel (MVVM)** مع **Repository Pattern** للفصل بين طبقات التطبيق.

```
┌─────────────────────────────────────────────────┐
│              UI Layer (View)                     │
│  Activities, Fragments, Adapters                │
└────────────────────┬────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────┐
│         ViewModel Layer                         │
│  Manages UI State & Business Logic              │
└────────────────────┬────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────┐
│         Repository Layer                        │
│  Abstracts Data Sources                         │
└────────────────────┬────────────────────────────┘
                     │
        ┌────────────┴────────────┐
        │                         │
┌───────▼────────┐      ┌────────▼──────────┐
│ Local Database │      │ Remote Database   │
│  (Room DB)     │      │   (Firebase)      │
└────────────────┘      └───────────────────┘
```

## هيكل الحزم (Packages)

### 1. `data` - طبقة البيانات

```
data/
├── models/              # نماذج البيانات
│   ├── User.kt
│   ├── Chat.kt
│   ├── Message.kt
│   ├── Status.kt
│   ├── Call.kt
│   ├── Report.kt
│   └── AdminStats.kt
├── repository/          # Repository Classes
│   ├── UserRepository.kt
│   ├── ChatRepository.kt
│   ├── MessageRepository.kt
│   └── CallRepository.kt
└── local/              # Local Database
    ├── AppDatabase.kt
    └── dao/
```

### 2. `ui` - طبقة العرض

```
ui/
├── main/                # الشاشة الرئيسية
│   └── MainActivity.kt
├── auth/                # شاشات المصادقة
│   ├── LoginActivity.kt
│   └── RegisterActivity.kt
├── chats/               # شاشات الدردشات
│   ├── ChatsFragment.kt
│   ├── ChatsAdapter.kt
│   └── ChatViewModel.kt
├── chat/                # شاشة الدردشة الفردية
│   ├── ChatActivity.kt
│   ├── MessageAdapter.kt
│   └── ChatDetailViewModel.kt
├── status/              # شاشات الحالات
│   ├── StatusFragment.kt
│   ├── StatusAdapter.kt
│   ├── StatusViewerActivity.kt
│   └── StatusViewModel.kt
├── calls/               # شاشات المكالمات
│   ├── CallsFragment.kt
│   ├── CallsAdapter.kt
│   ├── VideoCallActivity.kt
│   ├── AudioCallActivity.kt
│   └── CallViewModel.kt
├── settings/            # شاشات الإعدادات
│   ├── SettingsFragment.kt
│   └── SettingsViewModel.kt
└── admin/               # لوحة التحكم
    ├── AdminDashboardActivity.kt
    ├── AdminViewModel.kt
    └── AdminPagerAdapter.kt
```

### 3. `services` - الخدمات الخلفية

```
services/
├── MessagingService.kt      # خدمة الرسائل (FCM)
├── CallService.kt           # خدمة المكالمات
├── SyncService.kt           # خدمة المزامنة
└── NotificationService.kt   # خدمة الإشعارات
```

### 4. `utils` - الأدوات المساعدة

```
utils/
├── Constants.kt             # الثوابت
├── Extensions.kt            # امتدادات Kotlin
├── DateUtils.kt             # أدوات التاريخ والوقت
├── PermissionUtils.kt       # أدوات الأذونات
└── ValidationUtils.kt       # أدوات التحقق
```

## تدفق البيانات

### مثال: تحميل قائمة الدردشات

```
1. User opens ChatsFragment
   │
2. ChatsFragment creates ChatViewModel
   │
3. ChatViewModel calls ChatRepository.getChats()
   │
4. ChatRepository:
   - Checks local database first
   - If empty or outdated, fetches from Firebase
   - Saves to local database
   - Returns LiveData<List<Chat>>
   │
5. ChatsFragment observes LiveData
   │
6. UI updates with data
```

## Firebase Realtime Database Structure

```json
{
  "users": {
    "uid1": {
      "uid": "uid1",
      "displayName": "Ahmed",
      "phoneNumber": "+966501234567",
      "profileImageUrl": "https://...",
      "statusMessage": "Hello!",
      "isOnline": true,
      "lastSeen": 1234567890,
      "isAdmin": false,
      "isBanned": false,
      "createdAt": 1234567890,
      "blockedUsers": ["uid2"]
    }
  },
  "chats": {
    "chatId1": {
      "chatId": "chatId1",
      "participants": ["uid1", "uid2"],
      "lastMessage": "Hello!",
      "lastMessageTime": 1234567890,
      "lastMessageSender": "uid1",
      "isGroup": false
    }
  },
  "messages": {
    "chatId1": {
      "msgId1": {
        "messageId": "msgId1",
        "chatId": "chatId1",
        "senderId": "uid1",
        "content": "Hello!",
        "timestamp": 1234567890,
        "isRead": true
      }
    }
  },
  "status": {
    "statusId1": {
      "statusId": "statusId1",
      "userId": "uid1",
      "mediaUrl": "https://...",
      "timestamp": 1234567890,
      "expiresAt": 1234654290,
      "viewedBy": ["uid2", "uid3"]
    }
  },
  "calls": {
    "callId1": {
      "callId": "callId1",
      "callerId": "uid1",
      "receiverId": "uid2",
      "callType": "video",
      "status": "completed",
      "duration": 300
    }
  }
}
```

## مكونات رئيسية

### 1. Authentication Flow

```
Login/Register
    │
    ▼
Firebase Auth
    │
    ├─ Success ──▶ Create User in Database
    │
    └─ Failure ──▶ Show Error Message
```

### 2. Message Flow

```
User sends message
    │
    ▼
Save to local database
    │
    ▼
Upload to Firebase
    │
    ├─ Success ──▶ Update status
    │
    └─ Failure ──▶ Retry mechanism
```

### 3. Call Flow (WebRTC)

```
User initiates call
    │
    ▼
Send call signal via Firebase
    │
    ▼
Receiver accepts
    │
    ▼
Establish WebRTC connection
    │
    ├─ Audio/Video stream
    │
    └─ End call ──▶ Update call history
```

## المكتبات والتقنيات

| الطبقة | المكتبة | الغرض |
|------|--------|-------|
| UI | Material Design 3 | تصميم الواجهة |
| UI | Jetpack Compose | واجهات حديثة |
| Navigation | Navigation Component | التنقل بين الشاشات |
| Data | Firebase | قاعدة البيانات السحابية |
| Data | Room | قاعدة البيانات المحلية |
| Data | Retrofit | استدعاءات API |
| Async | Coroutines | العمليات غير المتزامنة |
| Reactive | LiveData | ملاحظة تغيرات البيانات |
| Media | WebRTC | المكالمات |
| Media | CameraX | التقاط الصور والفيديو |
| Images | Glide | تحميل الصور |

## أفضل الممارسات

### 1. Separation of Concerns
- كل طبقة لها مسؤولية واحدة
- الـ UI لا تتواصل مباشرة مع قاعدة البيانات

### 2. Reactive Programming
- استخدام LiveData و Flow
- تجنب callbacks المتداخلة

### 3. Error Handling
```kotlin
try {
    val result = repository.fetchData()
    // Handle success
} catch (e: Exception) {
    // Handle error
    Timber.e(e)
}
```

### 4. Naming Conventions
- Classes: `PascalCase` (MainActivity)
- Functions: `camelCase` (fetchMessages)
- Constants: `UPPER_SNAKE_CASE` (API_KEY)
- Private variables: `_camelCase` (_binding)

### 5. Null Safety
```kotlin
// استخدم safe calls
user?.displayName

// استخدم elvis operator
val name = user?.displayName ?: "Unknown"

// استخدم non-null assertion بحذر
val name = user!!.displayName
```

## الاختبار

### Unit Tests
```kotlin
class ChatRepositoryTest {
    @Test
    fun testFetchChats() {
        // Test implementation
    }
}
```

### Integration Tests
```kotlin
class ChatActivityTest {
    @Test
    fun testChatDisplayed() {
        // Test implementation
    }
}
```

## الأداء والتحسينات

1. **Lazy Loading:** تحميل البيانات عند الحاجة
2. **Caching:** حفظ البيانات محلياً
3. **Pagination:** تقسيم البيانات الكبيرة
4. **Image Optimization:** ضغط الصور قبل الرفع
5. **Background Sync:** مزامنة البيانات في الخلفية

---

**آخر تحديث:** مارس 2026
