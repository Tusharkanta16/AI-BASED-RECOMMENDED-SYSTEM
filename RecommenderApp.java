import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class RecommenderApp {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\dibya\\OneDrive\\Desktop\\codtech\\RecommendationSystem\\data\\user_preferences.csv");
            DataModel model = new FileDataModel(file);

            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            int userId = 3;
            List<RecommendedItem> recommendations = recommender.recommend(userId, 5);

            System.out.println("Recommendations for user ID " + userId + ":");

            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Recommended item ID: " + recommendation.getItemID() +
                                   ", Value: " + recommendation.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
