/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryPtDetailComponent } from 'app/entities/m-dictionary-pt/m-dictionary-pt-detail.component';
import { MDictionaryPt } from 'app/shared/model/m-dictionary-pt.model';

describe('Component Tests', () => {
  describe('MDictionaryPt Management Detail Component', () => {
    let comp: MDictionaryPtDetailComponent;
    let fixture: ComponentFixture<MDictionaryPtDetailComponent>;
    const route = ({ data: of({ mDictionaryPt: new MDictionaryPt(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryPtDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDictionaryPtDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryPtDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDictionaryPt).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
