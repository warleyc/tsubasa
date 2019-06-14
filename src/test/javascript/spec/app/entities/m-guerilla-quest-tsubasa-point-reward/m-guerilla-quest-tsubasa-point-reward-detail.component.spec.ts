/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestTsubasaPointRewardDetailComponent } from 'app/entities/m-guerilla-quest-tsubasa-point-reward/m-guerilla-quest-tsubasa-point-reward-detail.component';
import { MGuerillaQuestTsubasaPointReward } from 'app/shared/model/m-guerilla-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MGuerillaQuestTsubasaPointReward Management Detail Component', () => {
    let comp: MGuerillaQuestTsubasaPointRewardDetailComponent;
    let fixture: ComponentFixture<MGuerillaQuestTsubasaPointRewardDetailComponent>;
    const route = ({ data: of({ mGuerillaQuestTsubasaPointReward: new MGuerillaQuestTsubasaPointReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestTsubasaPointRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuerillaQuestTsubasaPointRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuerillaQuestTsubasaPointRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuerillaQuestTsubasaPointReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
