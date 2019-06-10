/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryJaDetailComponent } from 'app/entities/m-dictionary-ja/m-dictionary-ja-detail.component';
import { MDictionaryJa } from 'app/shared/model/m-dictionary-ja.model';

describe('Component Tests', () => {
  describe('MDictionaryJa Management Detail Component', () => {
    let comp: MDictionaryJaDetailComponent;
    let fixture: ComponentFixture<MDictionaryJaDetailComponent>;
    const route = ({ data: of({ mDictionaryJa: new MDictionaryJa(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryJaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDictionaryJaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryJaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDictionaryJa).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
