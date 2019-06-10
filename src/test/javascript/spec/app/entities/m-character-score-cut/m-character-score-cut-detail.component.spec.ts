/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCharacterScoreCutDetailComponent } from 'app/entities/m-character-score-cut/m-character-score-cut-detail.component';
import { MCharacterScoreCut } from 'app/shared/model/m-character-score-cut.model';

describe('Component Tests', () => {
  describe('MCharacterScoreCut Management Detail Component', () => {
    let comp: MCharacterScoreCutDetailComponent;
    let fixture: ComponentFixture<MCharacterScoreCutDetailComponent>;
    const route = ({ data: of({ mCharacterScoreCut: new MCharacterScoreCut(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCharacterScoreCutDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCharacterScoreCutDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCharacterScoreCutDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCharacterScoreCut).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
