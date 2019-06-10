/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestStageDetailComponent } from 'app/entities/m-advent-quest-stage/m-advent-quest-stage-detail.component';
import { MAdventQuestStage } from 'app/shared/model/m-advent-quest-stage.model';

describe('Component Tests', () => {
  describe('MAdventQuestStage Management Detail Component', () => {
    let comp: MAdventQuestStageDetailComponent;
    let fixture: ComponentFixture<MAdventQuestStageDetailComponent>;
    const route = ({ data: of({ mAdventQuestStage: new MAdventQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MAdventQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAdventQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAdventQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
