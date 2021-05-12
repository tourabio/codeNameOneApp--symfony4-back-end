<?php

namespace App\Controller;

use App\Repository\EvenementRepository;
use App\Repository\ParticipationRepository;
use App\Repository\ReclamationRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class ProfReclmataionController extends AbstractController
{
    /**
     * @Route("/prof/reclmataion", name="prof_reclmataion")
     */
    public function index(ReclamationRepository $reclamationRepository): Response
    {
      //  $profReclamation = $reclamationRepository->findBy(array( 'idUtilisateur'=> $user->getid() ));
        $profReclamation = $reclamationRepository->findBy(array( 'idutilisateur'=> '12345676' ));
        return $this->render('prof_reclmataion/index.html.twig', [
            'reclamations' => $profReclamation
        ]);
    }

    /**
     * @Route("/reclmataion/{search}", name="prof_reclamation_search", methods={"GET","POST"})
     */
    public function search(Request $request
        , UserRepository $userRepository
        ,NormalizerInterface $Normalizer
        ,ReclamationRepository $reclamationRepository
    ): Response
    {
        $search_input = $request->get('search_input') ;
        $user = $userRepository->find(5);
        $reclamations = $reclamationRepository->findBySujet( $search_input);
        $jsonContent = $Normalizer->normalize($reclamations, 'json',['groups'=> 'rec:read' , 'enable_max_depth' => true]);
        $retour=json_encode($jsonContent);
        return new Response($retour);
    }

}
