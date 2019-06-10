/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryArDetailComponent } from 'app/entities/m-dictionary-ar/m-dictionary-ar-detail.component';
import { MDictionaryAr } from 'app/shared/model/m-dictionary-ar.model';

describe('Component Tests', () => {
  describe('MDictionaryAr Management Detail Component', () => {
    let comp: MDictionaryArDetailComponent;
    let fixture: ComponentFixture<MDictionaryArDetailComponent>;
    const route = ({ data: of({ mDictionaryAr: new MDictionaryAr(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryArDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDictionaryArDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryArDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDictionaryAr).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
