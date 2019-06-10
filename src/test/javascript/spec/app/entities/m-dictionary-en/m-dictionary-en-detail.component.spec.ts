/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryEnDetailComponent } from 'app/entities/m-dictionary-en/m-dictionary-en-detail.component';
import { MDictionaryEn } from 'app/shared/model/m-dictionary-en.model';

describe('Component Tests', () => {
  describe('MDictionaryEn Management Detail Component', () => {
    let comp: MDictionaryEnDetailComponent;
    let fixture: ComponentFixture<MDictionaryEnDetailComponent>;
    const route = ({ data: of({ mDictionaryEn: new MDictionaryEn(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryEnDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDictionaryEnDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryEnDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDictionaryEn).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
