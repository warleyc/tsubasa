/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestTsubasaPointRewardDetailComponent } from 'app/entities/m-advent-quest-tsubasa-point-reward/m-advent-quest-tsubasa-point-reward-detail.component';
import { MAdventQuestTsubasaPointReward } from 'app/shared/model/m-advent-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MAdventQuestTsubasaPointReward Management Detail Component', () => {
    let comp: MAdventQuestTsubasaPointRewardDetailComponent;
    let fixture: ComponentFixture<MAdventQuestTsubasaPointRewardDetailComponent>;
    const route = ({ data: of({ mAdventQuestTsubasaPointReward: new MAdventQuestTsubasaPointReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestTsubasaPointRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MAdventQuestTsubasaPointRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAdventQuestTsubasaPointRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAdventQuestTsubasaPointReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
