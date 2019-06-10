/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryEsDetailComponent } from 'app/entities/m-dictionary-es/m-dictionary-es-detail.component';
import { MDictionaryEs } from 'app/shared/model/m-dictionary-es.model';

describe('Component Tests', () => {
  describe('MDictionaryEs Management Detail Component', () => {
    let comp: MDictionaryEsDetailComponent;
    let fixture: ComponentFixture<MDictionaryEsDetailComponent>;
    const route = ({ data: of({ mDictionaryEs: new MDictionaryEs(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryEsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDictionaryEsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryEsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDictionaryEs).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
