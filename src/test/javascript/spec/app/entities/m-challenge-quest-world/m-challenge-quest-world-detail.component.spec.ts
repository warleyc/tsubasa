/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestWorldDetailComponent } from 'app/entities/m-challenge-quest-world/m-challenge-quest-world-detail.component';
import { MChallengeQuestWorld } from 'app/shared/model/m-challenge-quest-world.model';

describe('Component Tests', () => {
  describe('MChallengeQuestWorld Management Detail Component', () => {
    let comp: MChallengeQuestWorldDetailComponent;
    let fixture: ComponentFixture<MChallengeQuestWorldDetailComponent>;
    const route = ({ data: of({ mChallengeQuestWorld: new MChallengeQuestWorld(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestWorldDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MChallengeQuestWorldDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChallengeQuestWorldDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mChallengeQuestWorld).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
