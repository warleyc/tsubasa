/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestTsubasaPointRewardDetailComponent } from 'app/entities/m-weekly-quest-tsubasa-point-reward/m-weekly-quest-tsubasa-point-reward-detail.component';
import { MWeeklyQuestTsubasaPointReward } from 'app/shared/model/m-weekly-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MWeeklyQuestTsubasaPointReward Management Detail Component', () => {
    let comp: MWeeklyQuestTsubasaPointRewardDetailComponent;
    let fixture: ComponentFixture<MWeeklyQuestTsubasaPointRewardDetailComponent>;
    const route = ({ data: of({ mWeeklyQuestTsubasaPointReward: new MWeeklyQuestTsubasaPointReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestTsubasaPointRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MWeeklyQuestTsubasaPointRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MWeeklyQuestTsubasaPointRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mWeeklyQuestTsubasaPointReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
