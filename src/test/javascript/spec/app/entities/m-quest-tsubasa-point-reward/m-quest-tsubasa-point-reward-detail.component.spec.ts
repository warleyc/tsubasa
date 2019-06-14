/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestTsubasaPointRewardDetailComponent } from 'app/entities/m-quest-tsubasa-point-reward/m-quest-tsubasa-point-reward-detail.component';
import { MQuestTsubasaPointReward } from 'app/shared/model/m-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MQuestTsubasaPointReward Management Detail Component', () => {
    let comp: MQuestTsubasaPointRewardDetailComponent;
    let fixture: ComponentFixture<MQuestTsubasaPointRewardDetailComponent>;
    const route = ({ data: of({ mQuestTsubasaPointReward: new MQuestTsubasaPointReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestTsubasaPointRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestTsubasaPointRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestTsubasaPointRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestTsubasaPointReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
