/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryFrDetailComponent } from 'app/entities/m-dictionary-fr/m-dictionary-fr-detail.component';
import { MDictionaryFr } from 'app/shared/model/m-dictionary-fr.model';

describe('Component Tests', () => {
  describe('MDictionaryFr Management Detail Component', () => {
    let comp: MDictionaryFrDetailComponent;
    let fixture: ComponentFixture<MDictionaryFrDetailComponent>;
    const route = ({ data: of({ mDictionaryFr: new MDictionaryFr(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryFrDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDictionaryFrDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryFrDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDictionaryFr).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
