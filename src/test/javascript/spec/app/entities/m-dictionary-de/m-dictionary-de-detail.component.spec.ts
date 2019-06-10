/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryDeDetailComponent } from 'app/entities/m-dictionary-de/m-dictionary-de-detail.component';
import { MDictionaryDe } from 'app/shared/model/m-dictionary-de.model';

describe('Component Tests', () => {
  describe('MDictionaryDe Management Detail Component', () => {
    let comp: MDictionaryDeDetailComponent;
    let fixture: ComponentFixture<MDictionaryDeDetailComponent>;
    const route = ({ data: of({ mDictionaryDe: new MDictionaryDe(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryDeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDictionaryDeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryDeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDictionaryDe).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
