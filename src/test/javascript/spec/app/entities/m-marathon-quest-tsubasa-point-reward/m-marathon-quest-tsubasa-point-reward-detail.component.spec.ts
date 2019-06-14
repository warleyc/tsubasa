/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestTsubasaPointRewardDetailComponent } from 'app/entities/m-marathon-quest-tsubasa-point-reward/m-marathon-quest-tsubasa-point-reward-detail.component';
import { MMarathonQuestTsubasaPointReward } from 'app/shared/model/m-marathon-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MMarathonQuestTsubasaPointReward Management Detail Component', () => {
    let comp: MMarathonQuestTsubasaPointRewardDetailComponent;
    let fixture: ComponentFixture<MMarathonQuestTsubasaPointRewardDetailComponent>;
    const route = ({ data: of({ mMarathonQuestTsubasaPointReward: new MMarathonQuestTsubasaPointReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestTsubasaPointRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonQuestTsubasaPointRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonQuestTsubasaPointRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonQuestTsubasaPointReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
