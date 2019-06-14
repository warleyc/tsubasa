/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestCoopRewardDetailComponent } from 'app/entities/m-quest-coop-reward/m-quest-coop-reward-detail.component';
import { MQuestCoopReward } from 'app/shared/model/m-quest-coop-reward.model';

describe('Component Tests', () => {
  describe('MQuestCoopReward Management Detail Component', () => {
    let comp: MQuestCoopRewardDetailComponent;
    let fixture: ComponentFixture<MQuestCoopRewardDetailComponent>;
    const route = ({ data: of({ mQuestCoopReward: new MQuestCoopReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestCoopRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestCoopRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestCoopRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestCoopReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
