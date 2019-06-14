/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildNegativeWordDetailComponent } from 'app/entities/m-guild-negative-word/m-guild-negative-word-detail.component';
import { MGuildNegativeWord } from 'app/shared/model/m-guild-negative-word.model';

describe('Component Tests', () => {
  describe('MGuildNegativeWord Management Detail Component', () => {
    let comp: MGuildNegativeWordDetailComponent;
    let fixture: ComponentFixture<MGuildNegativeWordDetailComponent>;
    const route = ({ data: of({ mGuildNegativeWord: new MGuildNegativeWord(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildNegativeWordDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuildNegativeWordDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildNegativeWordDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuildNegativeWord).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
