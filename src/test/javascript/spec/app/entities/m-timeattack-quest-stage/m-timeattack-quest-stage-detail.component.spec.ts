/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackQuestStageDetailComponent } from 'app/entities/m-timeattack-quest-stage/m-timeattack-quest-stage-detail.component';
import { MTimeattackQuestStage } from 'app/shared/model/m-timeattack-quest-stage.model';

describe('Component Tests', () => {
  describe('MTimeattackQuestStage Management Detail Component', () => {
    let comp: MTimeattackQuestStageDetailComponent;
    let fixture: ComponentFixture<MTimeattackQuestStageDetailComponent>;
    const route = ({ data: of({ mTimeattackQuestStage: new MTimeattackQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTimeattackQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTimeattackQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
